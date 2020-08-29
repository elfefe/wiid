package com.elfefe.coffeejoin.oltp.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.stfalcon.chatkit.commons.models.IMessage
import org.joda.time.DateTime
import java.util.*

@Entity(
    tableName = "message",
    foreignKeys = [ForeignKey(
        entity = Author::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("authorId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Message(
    @PrimaryKey
    val id: String,
    val authorId: String,
    val text: String,
    val createdAt: Date
)