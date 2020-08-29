package com.elfefe.wiid.controllers

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.elfefe.go4lunch.util.Shared
import com.elfefe.wiid.R
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider


class LoginActivity : AppCompatActivity() {
    private val gso: GoogleSignInOptions by lazy {
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }

    private val gsc: GoogleSignInClient by lazy { GoogleSignIn.getClient(this, gso) }

    private val callbackManager: CallbackManager by lazy { CallbackManager.Factory.create() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val buttonGoogle: Button = findViewById(R.id.activity_login_connection_google)
        val buttonFacebook: Button = findViewById(R.id.activity_login_connection_facebook)


        val permissions = listOf(
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

        if (!checkPermissions(permissions)) ActivityCompat.requestPermissions(
            this,
            permissions.toTypedArray(),
            1
        )

        LoginManager.getInstance().registerCallback(callbackManager, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult) {
                val accessToken = result.accessToken
                val isLoggedIn = accessToken != null && !accessToken.isExpired
                println("$TAG_FACEBOOK $accessToken")
                if (isLoggedIn)
                    handleFacebookAccessToken(accessToken)
            }

            override fun onCancel() {
                println("$TAG_FACEBOOK Connextion have been canceled.")
            }

            override fun onError(error: FacebookException?) {
                println("$TAG_FACEBOOK ${error!!.message}")
            }
        })

        buttonGoogle.setOnClickListener {
            signInGoogle()
        }

        buttonFacebook.setOnClickListener {
            signInFacebook()
        }
    }

    private fun signInGoogle() {
        val signInIntent = gsc.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN_GOOGLE)
    }

    private fun signInFacebook() {
        LoginManager.getInstance()
            .logInWithReadPermissions(this, arrayListOf("email", "public_profile"))
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseAuth.getInstance().currentUser?.let {
            updateUI(it)
        }
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN_GOOGLE) {
            val result = GoogleSignIn.getSignedInAccountFromIntent(data)
            println("$TAG_GOOGLE result: $result")

            if (result.isSuccessful) {
                val account = result.result
                firebaseAuthWithGoogle(account)
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount?) {
        val credential = GoogleAuthProvider.getCredential(acct?.idToken, null)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG_GOOGLE, "signInWithCredential:success")
                    FirebaseAuth.getInstance().currentUser?.let {
                        updateUI(it)
                    } ?: Toast.makeText(
                        applicationContext,
                        "Error login",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG_GOOGLE, "signInWithCredential:failure", task.exception)
                    Toast.makeText(applicationContext, "Authentication Failed.", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        FirebaseAuth.getInstance().signOut()
    }

    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG_FACEBOOK, "handleFacebookAccessToken:$token")

        val credential = FacebookAuthProvider.getCredential(token.token)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG_FACEBOOK, "signInWithCredential:success")
                    FirebaseAuth.getInstance().currentUser?.let {
                        updateUI(it)
                    }
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG_FACEBOOK, "signInWithCredential:failure", task.exception)
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    private fun updateUI(user: FirebaseUser) {
        getSharedPreferences(Shared.PREFERENCE, Context.MODE_PRIVATE)
            .edit()
            .putString(Shared.USER_MAIL, user.email)
            .putString(Shared.USER_ID, user.uid)
            .putString(Shared.USER_PICTURE_URL, user.photoUrl?.toString()!!)
            .putString(Shared.USER_NAME, user.displayName)
            .apply()

        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    private fun checkPermissions(permissions: List<String>): Boolean {
        permissions.forEach {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    it
                ) != PackageManager.PERMISSION_GRANTED
            ) return false
        }
        return true
    }

    companion object {
        private const val TAG_GOOGLE = "GoogleActivity"
        private const val TAG_FACEBOOK = "FacebookLogin"
        private const val RC_SIGN_IN_GOOGLE = 9001
    }
}