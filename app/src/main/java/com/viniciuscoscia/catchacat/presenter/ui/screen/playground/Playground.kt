@file:OptIn(DelicateCoroutinesApi::class)

package com.viniciuscoscia.catchacat.presenter.ui.screen.playground

import com.viniciuscoscia.catchacat.BuildConfig
import kotlinx.coroutines.*
import timber.log.Timber

// Test zone
object Playground {
    operator fun invoke() {
        if (BuildConfig.DEBUG) {
            execute()
        }
    }
}

private fun execute() = GlobalScope.launch(Dispatchers.IO) {
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

fun println(messageToPrint: String) {
    Timber.d(messageToPrint)
}