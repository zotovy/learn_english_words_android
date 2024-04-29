plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlinxSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.retrofit.serialization)
    implementation(libs.ktx.serialization)

    testImplementation(libs.junit)
    testImplementation(libs.ktx.coroutines.test)
    testImplementation(libs.retrofit.mockWebServer)
    testImplementation(libs.ktx.serialization)
}