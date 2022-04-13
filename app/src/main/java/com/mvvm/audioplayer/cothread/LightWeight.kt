package com.mvvm.audioplayer.cothread

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main() {

    GlobalScope.launch {
        println("Abhinay Aa gya")
        Thread.sleep(200)
        println("Abhinay chala gya")
    }

    Thread.sleep(500)

    println("Main execute ho gya pura")
}