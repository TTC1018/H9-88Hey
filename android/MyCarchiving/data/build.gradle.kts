plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.softeer.data"

    compileSdk = 33

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(project(":domain"))

    // flow
    implementation(libs.kotlinx.coroutines.core)

    // retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.gson)

    // paging
    implementation(libs.androidx.paging.common)
}