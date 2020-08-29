package com.elfefe.coffeejoin.utility

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class Converter {

    // Long
    @TypeConverter
    fun jsonToTimestamp(value: String?): ArrayList<Long>? {
        val listType: Type = object : TypeToken<ArrayList<Long>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun longListToJson(list: ArrayList<Long>?): String? {
        return Gson().toJson(list)
    }

    // String
    @TypeConverter
    fun jsonTostringList(value: String?): ArrayList<String>? {
        val listType: Type = object : TypeToken<ArrayList<String>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun stringListToJson(list: ArrayList<String>?): String? {
        return Gson().toJson(list)
    }
}