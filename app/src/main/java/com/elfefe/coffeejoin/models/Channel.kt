package com.elfefe.coffeejoin.models

data class Channel(
    private val id: Long = 0,
    private val name: String,
    private val author: List<Author>
)