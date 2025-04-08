
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.serialization)
    id("kotlin-parcelize")

}

android {
    namespace = "com.example.weathernowlater"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weathernowlater"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(project(":core"))
    implementation(project(":data"))
    implementation(project(":features"))
    implementation(project(":features:forecast"))
    implementation(project(":features:currentweather"))
    implementation(project(":features:cityInput"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)


    // Hilt
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.hilt.navigation.compose)

    //coil
    implementation(libs.coil.compose)

    //room
    implementation(libs.androidx.room.runtime )  // Room runtime library
    annotationProcessor(libs.androidx.room.compiler) // For annotation processing

    //noinspection KaptUsageInsteadOfKsp
    kapt(libs.androidx.room.compiler) // Kotlin code generation (if you're using Kotlin)

    implementation(libs.androidx.room.ktx)  // Kotlin extensions for coroutines (recommended)

    implementation(libs.kotlinx.serialization.json)

    implementation (libs.androidx.navigation.compose)

    implementation(libs.accompanist.systemuicontroller)

    testImplementation(libs.mockk.v11317)


}


kapt {
    correctErrorTypes = true
}

