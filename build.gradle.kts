plugins {
    id("com.android.application") version "8.5.2"
    id("org.jetbrains.kotlin.android") version "1.9.24"
}

layout.buildDirectory.set(file("/tmp/chessbuild"))

val copyKt = tasks.register<Copy>("copyKt") {
    from(projectDir) { include("*.kt") }
    into("/tmp/chesssrc")
}

tasks.matching { it.name.startsWith("compile") }.configureEach {
    dependsOn(copyKt)
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
            java.setSrcDirs(listOf("/tmp/chesssrc"))
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
}
