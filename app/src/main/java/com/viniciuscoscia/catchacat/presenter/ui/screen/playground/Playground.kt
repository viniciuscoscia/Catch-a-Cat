@file:OptIn(DelicateCoroutinesApi::class)
@file:Suppress("SuspendFunctionOnCoroutineScope")

package com.viniciuscoscia.catchacat.presenter.ui.screen.playground

import com.viniciuscoscia.catchacat.BuildConfig
import kotlinx.coroutines.*
import timber.log.Timber

// Test zone
object Playground {
    operator fun invoke() {
        if (BuildConfig.DEBUG) {
            GlobalScope.launch(Dispatchers.Default + CoroutineName("PlaygroundCoroutine")) {
                execute()
            }
        }
    }
}

private fun execute() {
    coroutineScope.cancel()
}

val coroutineScope = CoroutineScope(Dispatchers.IO + Job())

suspend fun concurrentSum(): Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }

    one.await() + two.await()
}

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

private fun coroutineTimeoutWrong() {
    println("Test")
    runBlocking(Dispatchers.Default) {
        repeat(100_000) { // Launch 100K coroutines
            launch {
                val resource = withTimeout(60) { // Timeout of 60 ms
                    delay(50)
                    Resource() // Acquire a resource and return it from withTimeout block
                }
                println("resource closing")
                resource.close() // Release the resource
            }
        }
    }
    // Outside of runBlocking all coroutines have completed
    println("acquired $acquired") // Print the number of resources still acquired
}

var acquired = 0

class Resource {
    init {
        acquired++
    } // Acquire the resource

    fun close() {
        acquired--
    } // Release the resource
}

// You can use like this when you need to do something just after a coroutine is canceled
private suspend fun CoroutineScope.tryFinally() {
    val job = launch {
        try {
            repeat(1000) { i ->
                println("job: I'm sleeping $i ...")
                delay(500L)
            }
        } finally {
            println("job: I'm running finally")
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

private suspend fun CoroutineScope.coroutineExample2Working() {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (isActive) { // cancellable computation loop
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

private suspend fun CoroutineScope.coroutineExample2NotWorkingCancellation() {
    val startTime = System.currentTimeMillis()
    val job = launch(Dispatchers.Default) {
        var nextPrintTime = startTime
        var i = 0
        while (i < 5) { // computation loop, just wastes CPU
            // print a message twice a second
            if (System.currentTimeMillis() >= nextPrintTime) {
                println("job: I'm sleeping ${i++} ...")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancelAndJoin() // cancels the job and waits for its completion
    println("main: Now I can quit.")
}

private suspend fun CoroutineScope.coroutineExample1() {
    val job = launch {
        repeat(1000) { i ->
            println("job: I'm sleeping $i ...")
            delay(500L)
        }
    }
    delay(1300L) // delay a bit
    println("main: I'm tired of waiting!")
    job.cancel() // cancels the job
    job.join() // waits for job's completion
    println("main: Now I can quit.")
}

private fun println(messageToPrint: String) {
    Timber.d(messageToPrint)
}