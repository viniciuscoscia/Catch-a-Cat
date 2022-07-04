package com.viniciuscoscia.catchacat.presenter

import android.app.Application
import com.amplifyframework.AmplifyException
import com.amplifyframework.auth.AuthException
import com.amplifyframework.auth.AuthSession
import com.amplifyframework.auth.AuthUserAttributeKey
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin
import com.amplifyframework.auth.options.AuthSignUpOptions
import com.amplifyframework.core.Amplify
import com.viniciuscoscia.catchacat.BuildConfig
import com.viniciuscoscia.catchacat.data.di.dataModule
import com.viniciuscoscia.catchacat.domain.di.domainModule
import com.viniciuscoscia.catchacat.presenter.di.presenterModule
import com.viniciuscoscia.catchacat.presenter.ui.screen.playground.Playground
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class CatchACatApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        // TODO Clean up this file
        setupKoin()
        Playground()
//        setupAmplify()
//        fetchAuthSession()
//        test()
    }

    private fun test() {
        val options = AuthSignUpOptions.builder()
            .userAttribute(AuthUserAttributeKey.email(), "vinicius.coscia@live.com")
            .build()

        Amplify.Auth.signUp("vinicius.coscia", "!", options,
            { Timber.i("Sign up succeeded: $it") },
            { Timber.e("Sign up failed", it) }
        )

        Amplify.Auth.confirmSignUp(
            "vinicius.coscia", "787615",
            { result ->
                if (result.isSignUpComplete) {
                    Timber.i("Confirm signUp succeeded")
                } else {
                    Timber.i("Confirm sign up not complete")
                }
            },
            { Timber.e("Failed to confirm sign up", it) }
        )
    }

    private fun fetchAuthSession() {
        Amplify.Auth.fetchAuthSession(::onFetchAuthSuccess, ::onFetchAuthError)
    }

    private fun onFetchAuthSuccess(authSession: AuthSession) {
        Timber.i("Auth session = $authSession")
    }

    private fun onFetchAuthError(authException: AuthException) {
        Timber.e("Failed to fetch auth session", authException)
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@CatchACatApplication)
            androidLogger(Level.ERROR)
            modules(presenterModule + dataModule + domainModule)
        }
    }

    private fun setupAmplify() {
        try {
            Amplify.addPlugin(AWSCognitoAuthPlugin())
            Amplify.configure(applicationContext)
            Timber.i("Initialized Amplify")
        } catch (error: AmplifyException) {
            Timber.e("Could not initialize Amplify", error)
        }
    }
}