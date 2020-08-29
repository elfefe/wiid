package com.elfefe.coffeejoin.oltp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "channel"
)
data class Channel(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val name: String,
    val authors: List<String>
)