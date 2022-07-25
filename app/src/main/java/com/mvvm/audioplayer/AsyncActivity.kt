package com.mvvm.audioplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class AsyncActivity : AppCompatActivity() {
    var btn: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)
        btn = findViewById(R.id.btn)

        btn?.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val time = measureTimeMillis {
                    val async1:Deferred<String> = async {
                        println("working on :${Thread.currentThread().name}")
                        getResult1()
                    }

                  val async2:Deferred<String> = async {
                        println("working on :${Thread.currentThread().name}")
                        getResult2()
                    }

                        async1.await()
                    async2.await()


                }
                println("Total elapsed time is: $time")
            }


        }
    }

    suspend fun getResult1():String {

        delay(1000)
        return "result1"
    }

    suspend fun getResult2() : String{

        delay(2000)
        return "result2"
    }


}