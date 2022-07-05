plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinSerialization)
    id(Plugins.kotlinKapt)
    id(Plugins.koin)
    id(Plugins.parcelize)
    id(Plugins.secrets)
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion

        applicationId = Configs.applicationId
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        buildConfigField("String", "BaseUrl", "\"https://api.thecatapi.com/v1/\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }
}

dependencies {
    // Core
    implementation(Libs.kotlinStdLib)
    implementation(Libs.ktxCore)
    implementation(Libs.multidex)

    // Android
    implementation(Libs.androidAppCompat)
    implementation(Libs.androidMaterial)

    // Jetpack LifeCycle
    implementation(Libs.lifecycleRuntime)
    implementation(Libs.lifecycleCommon)
    implementation(Libs.lifecycleViewModel)

    // Koin
    implementation(Libs.koinAndroid)
    implementation(Libs.koinCompose)

    // Coroutine
    implementation(Libs.coroutines)
    implementation(Libs.coroutinesAndroid)

    // Compose
    implementation(Libs.composeMaterial)
    implementation(Libs.composeUi)
    implementation(Libs.composeToolingPreview)
    implementation(Libs.composeFoundation)
    implementation(Libs.composeFoundationLayout)
    implementation(Libs.activityCompose)
    implementation(Libs.navigationCompose)
    implementation(Libs.navigationAnimation)
    implementation(Libs.composeConstraintLayout)
    implementation(Libs.composePager)
    debugImplementation(Libs.composeTooling)

    // Coil
    implementation(Libs.coil)
    implementation(Libs.coilGif)
    implementation(Libs.coilCompose)

    // Room
    implementation(Libs.roomRuntime)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    // Ktor
    implementation(Libs.ktorAndroid)
    implementation(Libs.ktorLogging)
    implementation(Libs.ktorSerialization)
    implementation(Libs.ktorContentNegotiation)
    implementation(Libs.kotlinSerialization)

    // Moshi
    implementation(Libs.moshi)
    implementation(Libs.moshiKotlin)
    kapt(Libs.moshiCodegenKapt)

    // Paging
    implementation(Libs.pagingCompose)

    // Log
    implementation(Libs.timber)

    // Test
    testImplementation(Libs.jUnit)
    testImplementation(Libs.coroutineTest)
    androidTestImplementation(Libs.androidExtJUnit)
    androidTestImplementation(Libs.androidEspresso)

    // AWS
    implementation(Libs.amplify)
    implementation(Libs.amplifyCognito)
}