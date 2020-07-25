package com.andysoft.test.room

import android.graphics.Bitmap
import androidx.room.TypeConverter
import com.andysoft.test.models.local.CoverPhoto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ADConverters {
    @TypeConverter
    fun toByteArray(value: String): ByteArray? {
        val listType: Type = object : TypeToken<ByteArray?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromByteArray(list: ByteArray?): String {
        return Gson().toJson(list)
    }
}