plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.example.jetpackpracticeall"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.jetpackpracticeall"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        enable = true
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Coroutines
    val coroutineCoreVersion = "1.7.1"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineCoreVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineCoreVersion")

    // Coroutine Lifecycle Scopes
    val coroutineLifecycleVersion = "2.7.0"
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:$coroutineLifecycleVersion")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:$coroutineLifecycleVersion")
    implementation ("androidx.activity:activity-ktx:1.8.2")

    // live-data
    val lifecycle_version = "2.7.0"
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")

    // Retrofit
    val retrofitVer = "2.9.0"
    implementation ("com.squareup.retrofit2:retrofit:$retrofitVer")
    implementation ("com.squareup.retrofit2:converter-gson:$retrofitVer")
    implementation ("com.squareup.okhttp3:okhttp:4.9.3")

    // Room
    val room_version = "2.6.1"
    implementation ("androidx.room:room-runtime:$room_version")
    kapt ("androidx.room:room-compiler:$room_version")
    implementation ("androidx.room:room-ktx:$room_version")

    // work
    implementation ("androidx.work:work-runtime-ktx:2.9.0")

    // testing

    // roboelectric
    val robolectricVer = "4.11.1"
    testImplementation("org.robolectric:robolectric:$robolectricVer")

    // mockito
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-core:4.6.1")
    testImplementation("org.mockito:mockito-inline:4.1.0")
    testImplementation("io.mockk:mockk:1.12.0")

    // kotlin corourtines testing
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")

    // android test implementation
    val androidTestImpVer = "1.5.0"
    testImplementation ("androidx.test:core:$androidTestImpVer")

    // Testing WorkManager
    testImplementation("androidx.work:work-testing:2.9.0")

// For Making Assertions in Test Cases
    testImplementation("com.google.truth:truth:1.1.3")
}