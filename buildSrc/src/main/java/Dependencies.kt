object Versions {
    const val composePager = "0.23.1"
    const val androidAppCompat = "1.4.1"
    const val androidExtJUnit = "1.1.3"
    const val androidEspresso = "3.4.0"
    const val androidMaterial = "1.6.0"
    const val androidPlugin = "7.2.1"
    const val activityCompose = "1.4.0"
    const val coil = "2.1.0"
    const val compose = "1.2.0-beta03"
    const val composeConstraintLayout = "1.0.1"
    const val jUnit = "4.13.2"
    const val koin = "3.2.0"
    const val kotlin = "1.6.21"
    const val kotlinSerialization = "1.3.3"
    const val ktor = "2.0.2"
    const val ktx = "1.7.0"
    const val kotlinCoroutines = "1.6.2"
    const val lifecycle = "2.4.1"
    const val moshi = "1.13.0"
    const val multidex = "2.0.1"
    const val navigationCompose = "2.4.2"
    const val pagingCompose = "1.0.0-alpha14"
    const val room = "2.4.2"
    const val secretsPlugin = "2.0.1"
    const val timber = "5.0.1"
}

object Libs {
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val androidAppCompat = "androidx.appcompat:appcompat:${Versions.androidAppCompat}"
    const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
    const val androidExtJUnit = "androidx.test.ext:junit:${Versions.androidExtJUnit}"
    const val androidEspresso = "androidx.test.espresso:espresso-core:${Versions.androidEspresso}"
    const val androidNavigationCompose =
        "androidx.navigation:navigation-compose:${Versions.navigationCompose}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
    const val coilGif = "io.coil-kt:coil-gif:${Versions.coil}"
    const val coilCompose = "io.coil-kt:coil-compose:${Versions.coil}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
    const val composeConstraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Versions.composeConstraintLayout}"
    const val composeFoundation = "androidx.compose.foundation:foundation:${Versions.compose}"
    const val composePager = "com.google.accompanist:accompanist-pager:${Versions.composePager}"
    const val composeFoundationLayout =
        "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Versions.compose}"
    const val coroutineTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kotlinCoroutines}"
    const val composeTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Versions.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Versions.compose}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val koinCompose = "io.insert-koin:koin-androidx-compose:${Versions.koin}"
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlinSerialization =
        "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.kotlinSerialization}"
    const val ktorAndroid = "io.ktor:ktor-client-android:${Versions.ktor}"
    const val ktorContentNegotiation = "io.ktor:ktor-client-content-negotiation:${Versions.ktor}"
    const val ktorSerialization = "io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}"
    const val ktorLogging = "io.ktor:ktor-client-logging:${Versions.ktor}"
    const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiCodegenKapt = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val multidex = "androidx.multidex:multidex:${Versions.multidex}"
    const val pagingCompose = "androidx.paging:paging-compose:${Versions.pagingCompose}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object Plugins {
    const val parcelize = "kotlin-parcelize"
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinSerialization = "kotlinx-serialization"
    const val kotlinKapt = "kotlin-kapt"
    const val koin = "koin"
    const val secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
}

object ClassPaths {
    const val androidPlugin = "com.android.tools.build:gradle:${Versions.androidPlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val koinPlugin = "io.insert-koin:koin-gradle-plugin:${Versions.koin}"
    const val koinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    const val secrets = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.secretsPlugin}"
}

const val latestSdk = 32

object Configs {
    const val applicationId = "com.viniciuscoscia.catchacat"
    const val compileSdkVersion = latestSdk
    const val minSdkVersion = 21
    const val targetSdkVersion = latestSdk
    const val versionCode = 1
    const val versionName = "1.0.0"
}