plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltPlugin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "dev.zotov.learnenglishwords"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.zotov.learnenglishwords"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "dev.zotov.learnenglishwords.utils.CustomTestRunner"
    }

    buildTypes {
        debug {
            buildConfigField("String", "WORDS_API_BASE_URL", "\"https://api.dictionaryapi.dev\"")
        }

        release {
            buildConfigField("String", "WORDS_API_BASE_URL", "\"https://api.dictionaryapi.dev\"")

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
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.retrofit.mockWebServer)
    androidTestImplementation(libs.hilt.test)
    kspAndroidTest(libs.hilt.compiler)
    androidTestImplementation(libs.mockito.android)
    androidTestImplementation(libs.mockito.ktx)
    androidTestImplementation(libs.ktx.coroutines.test)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.room.ktx)

    implementation(project(":database"))
    implementation(project(":shared"))
    implementation(project(":words-data"))
    implementation(project(":words-api"))
    implementation(project(":features:word-game"))
    implementation(project(":features:word-info"))
}