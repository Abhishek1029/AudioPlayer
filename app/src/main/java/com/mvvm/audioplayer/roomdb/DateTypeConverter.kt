package com.mvvm.audioplayer.roomdb

import androidx.room.TypeConverter
import java.util.*

object DateTypeConverter {
    @TypeConverter
    fun toDate(value: Long?): Date? {
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun toLong(value: Date?): Long? {
        return value?.time
    }
}