package com.elfefe.coffeejoin.models

import android.os.Parcel
import android.os.Parcelable
import com.stfalcon.chatkit.commons.models.IUser

data class Author(
    private val id: String = "",
    private val name: String = "",
    private val avatar: String = ""
) : IUser, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun getAvatar(): String {
        return avatar
    }

    override fun getName(): String {
        return name
    }

    override fun getId(): String {
        return id
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(avatar)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Author> {
        override fun createFromParcel(parcel: Parcel): Author {
            return Author(parcel)
        }

        override fun newArray(size: Int): Array<Author?> {
            return arrayOfNulls(size)
        }
    }
}