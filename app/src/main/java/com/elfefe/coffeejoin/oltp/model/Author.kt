package com.elfefe.coffeejoin.oltp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "author",
    foreignKeys = [ForeignKey(
        entity = ChannelPrivate::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("channelId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Author(
    @PrimaryKey
    val id: String,
    val channelId: Long,
    val name: String,
    val avatar: String,
    val messages: List<String>
)