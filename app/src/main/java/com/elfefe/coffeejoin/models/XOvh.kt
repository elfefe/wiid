package com.elfefe.coffeejoin.models

enum class XOvh(val type: String) {
    APPLICATION("X-Ovh-Application"),
    TIMESTAMP("X-Ovh-Timestamp"),
    SIGNATURE("X-Ovh-Signature"),
    CONSUMER("X-Ovh-Consumer")
}