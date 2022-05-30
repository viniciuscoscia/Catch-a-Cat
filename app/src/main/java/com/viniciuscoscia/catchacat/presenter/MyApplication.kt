package com.viniciuscoscia.catchacat.presenter

import android.app.Application
import com.viniciuscoscia.catchacat.BuildConfig
import com.viniciuscoscia.catchacat.data.di.dataModule
import com.viniciuscoscia.catchacat.domain.di.domainModule
import com.viniciuscoscia.catchacat.presenter.di.presenterModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@MyApplication)
            androidLogger(Level.ERROR)
            modules(presenterModule + dataModule + domainModule)
        }
    }
}