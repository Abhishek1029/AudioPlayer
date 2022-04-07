package com.mvvm.audioplayer.utils.extensions

import android.database.Cursor
import java.lang.Exception
import java.lang.IllegalArgumentException

fun Cursor.getString(columnName: String): String {
    try {
        return getString(getColumnIndexOrThrow(columnName))
    } catch (exception: Exception) {
        throw IllegalArgumentException("invalid column $columnName", exception)
    }
}