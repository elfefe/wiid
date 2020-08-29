package com.elfefe.coffeejoin.models.credential

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AccessRule(
    val method: String,
    val path: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(method)
        parcel.writeString(path)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccessRule> {
        override fun createFromParcel(parcel: Parcel): AccessRule {
            return AccessRule(parcel)
        }

        override fun newArray(size: Int): Array<AccessRule?> {
            return arrayOfNulls(size)
        }
    }
}