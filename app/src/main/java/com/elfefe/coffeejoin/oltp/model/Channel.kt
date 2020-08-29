package com.elfefe.coffeejoin.oltp.model

import androidx.room.Entity

abstract class Channel(
    open val id: Long,
    open val name: String,
    open val authorIds: List<String>
)