package com.elfefe.coffeejoin.oltp

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.elfefe.coffeejoin.models.Author
import com.elfefe.coffeejoin.models.Channel
import com.elfefe.coffeejoin.models.Message

@Entity(tableName = "communication")
data class Communication(
    @PrimaryKey(autoGenerate = true)
    val id: Long
)