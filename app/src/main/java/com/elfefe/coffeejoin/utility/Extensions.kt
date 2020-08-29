package com.elfefe.coffeejoin.utility

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color.YELLOW
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.NORMAL
import android.util.Log
import androidx.core.app.ActivityCompat
import com.elfefe.coffeejoin.controllers.BaseApplication
import kotlinx.coroutines.flow.Flow
import java.lang.Exception
import java.net.URL
import java.text.AttributedCharacterIterator
import javax.net.ssl.HttpsURLConnection

fun checkPermissions(activity: AppCompatActivity, vararg permissions: String): Boolean {
    permissions.forEach {
        if (ActivityCompat.checkSelfPermission(
                activity,
                it
            ) != PackageManager.PERMISSION_GRANTED
        ) ActivityCompat.requestPermissions(
            activity,
            permissions,
            1
        )
        else return true
    }
    return false
}

val prefs: SharedPreferences = BaseApplication.INSTANCE.getSharedPreferences(PreferenceUtils.KEY, Context.MODE_PRIVATE)

fun Any.log(toLog: String) {
    Log.i(javaClass.simpleName, toLog)
}

fun Any.ping(urlToPing: String): String {
    var content = ""
    val url = URL(urlToPing)
    val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection
    try {
        connection.connect()
        log(connection.responseCode.toString())
        content = connection.inputStream.bufferedReader().readText()
    } catch (e: Exception) {
        e.stackTrace
    } finally {
        connection.disconnect()
    }
    return content
}

const val OVH_URL = "https://eu.api.ovh.com/1.0/"
const val OVH_TIMESTAMP_URL = "https://eu.api.ovh.com/1.0/auth/time"

const val APP_KEY = "2bfeZZQFTo4iCAGC"
const val APP_SECRET = "rpGsWPAOPppuznruc205x3wKaaumotZy"

const val SERVICE_NAME = "0309d65eb057415882b1301e0e3b9090"
const val CONTAINER_MESSAGERIE= "6257567a6332466e5a584a705a533548556b453d"