plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    alias(libs.plugins.serialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.cityinput"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
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
    implementation((project(":features:currentweather")))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.0")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.0")
    testImplementation("org.mockito:mockito-core:3.11.2")
    testImplementation("org.mockito.kotlin:mockito-kotlin:4.0.0")
// For Kotlin support
    testImplementation("androidx.arch.core:core-testing:2.1.0")
// For LiveData/StateFlow testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.0")
// For coroutines testing



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
}

kapt {
    correctErrorTypes = true
}
