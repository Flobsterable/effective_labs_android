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
}