package com.elfefe.wiid.models

import android.icu.util.Calendar
import com.stfalcon.chatkit.commons.models.IMessage
import org.joda.time.DateTime
import java.time.Instant
import java.util.*

data class Message(
    private val id: String = "",
    private val text: String = "",
    private val author: Author = Author(),
    private val createdAt: Date = DateTime.now().toDate()
) : IMessage {
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
}