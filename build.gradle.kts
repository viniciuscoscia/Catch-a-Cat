// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(ClassPaths.androidPlugin)
        classpath(ClassPaths.kotlinPlugin)
        classpath(ClassPaths.koinPlugin)
        classpath(ClassPaths.koinSerialization)
        classpath(ClassPaths.secrets)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}