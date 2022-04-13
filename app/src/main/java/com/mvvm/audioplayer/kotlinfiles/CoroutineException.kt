package com.mvvm.audioplayer.kotlinfiles

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

fun main() {
    try {
        try {
            println("Number is ${5 / 0}")
        } catch (e: Exception) {
          //  println("Exception aa gya bhai sahab")
        }
        println("Nadeem iska answer btao")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}