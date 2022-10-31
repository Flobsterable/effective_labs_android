object Dependencies {

    object Compose {

        const val compose_version = "1.4.0-alpha01"

        const val coil = "io.coil-kt:coil-compose:2.2.2"

        const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:0.25.1"

        const val ui = "androidx.compose.ui:ui:$compose_version"
        const val material = "androidx.compose.material:material:$compose_version"
        const val preview = "androidx.compose.ui:ui-tooling-preview:$compose_version"
        const val livedata = "androidx.compose.runtime:runtime-livedata:$compose_version"

        const val composeJUnit = "androidx.compose.ui:ui-test-junit4:$compose_version"
        const val composeUITooling = "androidx.compose.ui:ui-tooling:$compose_version"
        const val composeManifest ="androidx.compose.ui:ui-test-manifest:$compose_version"
    }

    object Navigation {
        private const val nav_version = "2.5.3"
        const val navigation = "androidx.navigation:navigation-compose:$nav_version"
    }

    object Hilt {
        private const val version = "2.44"
        const val navigation = "androidx.hilt:hilt-navigation-compose:1.0.0"
        const val hilt = "com.google.dagger:hilt-android:$version"
        const val compiler = "com.google.dagger:hilt-compiler:$version"
    }

    object Android {
        const val coreKtx = "androidx.core:core-ktx:1.7.20"
    }

    object Detekt {
        const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.22.0-RC2"
    }

    object Lifecycle {
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.5.1"
        const val activityCompose = "androidx.activity:activity-compose:1.6.1"
    }

    object Test {
        const val jUnit = "junit:junit:4.13.2"
        const val androidJUnit = "androidx.test.ext:junit:1.1.3"
        const val espresso = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Retrofit {
        const val okHttpInterception = "com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val converterScalars = "com.squareup.retrofit2:converter-scalars:2.9.0"
        const val coroutinesAdapter ="com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val converterMoshi = "com.squareup.retrofit2:converter-moshi:2.9.0"

        const val moshi = "com.squareup.moshi:moshi:1.14.0"
        const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.12.0"
        const val moshiCompiler = "com.squareup.moshi:moshi-kotlin-codegen:1.14.0"
    }
}