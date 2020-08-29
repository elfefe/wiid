package com.elfefe.coffeejoin.models.credential

import android.os.Parcel
import android.os.Parcelable
import com.elfefe.coffeejoin.models.credential.AccessRule
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Credentials(
    val accessRules: List<AccessRule>,
    val redirection: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.createTypedArrayList(AccessRule)!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(accessRules)
        parcel.writeString(redirection)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Credentials> {
        override fun createFromParcel(parcel: Parcel): Credentials {
            return Credentials(parcel)
        }

        override fun newArray(size: Int): Array<Credentials?> {
            return arrayOfNulls(size)
        }
    }
}