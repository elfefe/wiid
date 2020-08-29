package com.elfefe.coffeejoin.models

import android.os.Parcel
import android.os.Parcelable
import com.stfalcon.chatkit.commons.models.IMessage
import org.joda.time.DateTime
import java.util.*

data class Message(
    private val id: String = "",
    private val text: String = "",
    private val author: Author = Author(),
    private val createdAt: Date = DateTime.now().toDate()
) : IMessage, Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readParcelable<Author>(Author::class.java.classLoader)!!,
        parcel.readSerializable() as Date
    )

    override fun getId(): String {
        return id
    }

    override fun getText(): String {
        return text
    }

    override fun getUser(): Author {
        return author
    }

    override fun getCreatedAt(): Date {
        return createdAt
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(text)
        parcel.writeParcelable(author, 0)
        parcel.writeSerializable(createdAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Message> {
        override fun createFromParcel(parcel: Parcel): Message {
            return Message(parcel)
        }

        override fun newArray(size: Int): Array<Message?> {
            return arrayOfNulls(size)
        }
    }
}