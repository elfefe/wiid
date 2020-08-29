package com.elfefe.coffeejoin.models

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import com.elfefe.coffeejoin.oltp.model.ChannelCommunity
import com.elfefe.coffeejoin.oltp.model.ChannelPrivate
import com.google.android.gms.internal.firebase_auth.zzff
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.*

class User() {
    val id = FirebaseAuth.getInstance().currentUser!!.uid
    val email = FirebaseAuth.getInstance().currentUser!!.email
    val name = FirebaseAuth.getInstance().currentUser!!.displayName
    val phoneNumber = FirebaseAuth.getInstance().currentUser!!.phoneNumber
    val photoUrl = FirebaseAuth.getInstance().currentUser!!.photoUrl
    val isAnonymous = FirebaseAuth.getInstance().currentUser!!.isAnonymous

    var communityChannel: ChannelCommunity? = null
    var privateChannel: ChannelPrivate? = null

    companion object {
        var INFO: User = User()
    }
}