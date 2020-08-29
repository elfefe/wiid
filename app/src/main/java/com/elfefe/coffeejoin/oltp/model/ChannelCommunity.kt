package com.elfefe.coffeejoin.oltp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "channel_private"
)
data class ChannelPrivate(
    @PrimaryKey
    override val id: Long,
    override val name: String,
    override val authorIds: List<String>) :
    Channel(id, name, authorIds)