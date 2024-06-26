plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")

}

android {
    namespace = "com.example.genericapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.genericapp"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    val retrofitVersion = "2.9.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    val nav_version = "2.7.7"
    implementation("androidx.navigation:navigation-compose:$nav_version")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    implementation("com.google.dagger:hilt-android:2.49")
    kapt("com.google.dagger:hilt-android-compiler:2.49")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    implementation("com.squareup.moshi:moshi-kotlin:1.15.1")

    implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
    implementation("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation ("com.squareup.moshi:moshi:1.15.1")

    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")

    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")

    // Mockito core library
    testImplementation("org.mockito:mockito-core:4.6.1")

    // Mockito inline if you need to mock final classes
    testImplementation("org.mockito:mockito-inline:4.6.1")

    testImplementation ("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")

    testImplementation ("io.mockk:mockk:1.12.0")

    testImplementation("app.cash.turbine:turbine:0.12.1")




    // Mockito Kotlin support

    // For AndroidX Test
    testImplementation("androidx.test:core:1.6.0")
    testImplementation("androidx.test.ext:junit:1.2.0")
    testImplementation("androidx.test:runner:1.6.0")

    // JUnit
    testImplementation("junit:junit:4.13.2")

    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.4")

    testImplementation("com.google.truth:truth:1.1.3")



}

kapt {
    correctErrorTypes = true
}