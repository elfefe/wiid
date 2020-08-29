package com.elfefe.coffeejoin.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class RoomConverter {
    @TypeConverter
    fun jsonToTimestamp(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun longListToJson(list: ArrayList<Long?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun stringListToJson(list: ArrayList<String?>?): String? {
        return Gson().toJson(list)
    }
}