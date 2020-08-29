package com.elfefe.coffeejoin.models.consumervalidation

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ConsumerValidation(
    val validationUrl: String,
    val consumerKey: String,
    val state: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(validationUrl)
        parcel.writeString(consumerKey)
        parcel.writeString(state)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ConsumerValidation> {
        override fun createFromParcel(parcel: Parcel): ConsumerValidation {
            return ConsumerValidation(parcel)
        }

        override fun newArray(size: Int): Array<ConsumerValidation?> {
            return arrayOfNulls(size)
        }
    }
}