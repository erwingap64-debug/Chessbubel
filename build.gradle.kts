import org.gradle.api.tasks.util.PatternFilterable

plugins {
    id("com.android.application") version "8.5.2"
    id("org.jetbrains.kotlin.android") version "1.9.24"
}

android {
    namespace = "com.example.chessbubble"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.chessbubble"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.setSrcDirs(listOf("."))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}

tasks.configureEach {
    if (name.startsWith("compile") && (name.endsWith("Kotlin") || name.endsWith("JavaWithJavac"))) {
        (this as? PatternFilterable)?.exclude("build/**", ".gradle/**", ".github/**")
    }
}
