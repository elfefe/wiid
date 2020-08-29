package com.elfefe.coffeejoin.oltp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.elfefe.coffeejoin.utility.Converter

@Entity(
    tableName = "channel_community"
)
data class ChannelCommunity(
    @PrimaryKey
    var name: String = "",
    var latitude: Double = 0.0,
    var longitude: Double = 0.0,
    @TypeConverters(Converter::class)
    var authorIds: ArrayList<String> = arrayListOf()
)