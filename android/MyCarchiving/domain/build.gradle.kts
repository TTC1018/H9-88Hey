plugins {
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    // hilt annotation
    implementation(libs.javax.inject)

    // flow
    implementation(libs.kotlinx.coroutines.core)
}