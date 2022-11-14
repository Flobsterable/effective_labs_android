plugins {
    id ("com.android.application")
    kotlin ("android")
    kotlin ("kapt")
    id ("dagger.hilt.android.plugin")
    id ("io.gitlab.arturbosch.detekt")
}

android {
    compileSdk = Config.compileSdk

    defaultConfig {
        applicationId =  Config.packageName
        minSdk = Config.minSDK
        targetSdk = Config.targetSDK
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = Config.testRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packagingOptions {
        resources.excludes.add("META-INF/*")
    }
}

dependencies {

    implementation (Dependencies.Detekt.detekt)

    implementation (Dependencies.Compose.accompanist)

    implementation (Dependencies.Navigation.navigation)

    implementation (Dependencies.Compose.coil)

    implementation (Dependencies.Hilt.navigation)
    implementation (Dependencies.Hilt.hilt)
    kapt (Dependencies.Hilt.compiler)

    implementation (Dependencies.Retrofit.okHttpInterception)
    implementation (Dependencies.Retrofit.retrofit)
    implementation (Dependencies.Retrofit.converterScalars)
    implementation (Dependencies.Retrofit.coroutinesAdapter)
    implementation (Dependencies.Retrofit.converterMoshi)

    implementation(Dependencies.Retrofit.moshi)
    implementation(Dependencies.Retrofit.moshiKotlin)
    kapt(Dependencies.Retrofit.moshiCompiler)

    implementation (Dependencies.Android.coreKtx)
    implementation (Dependencies.Compose.ui)
    implementation (Dependencies.Compose.material)
    implementation (Dependencies.Compose.preview)
    implementation (Dependencies.Compose.livedata)
    implementation (Dependencies.Lifecycle.lifecycleKtx)
    implementation (Dependencies.Lifecycle.activityCompose)
    testImplementation (Dependencies.Test.jUnit)
    androidTestImplementation (Dependencies.Test.androidJUnit)
    androidTestImplementation (Dependencies.Test.espresso)
    androidTestImplementation (Dependencies.Compose.composeJUnit)
    debugImplementation (Dependencies.Compose.composeUITooling)
    debugImplementation (Dependencies.Compose.composeManifest)
}

kapt {
    correctErrorTypes = true
}