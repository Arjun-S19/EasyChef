// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
<<<<<<< HEAD
}

android {

    buildFeatures {
        viewBinding = true

    }
=======
    alias(libs.plugins.kotlin.compose) apply false
    id("com.google.dagger.hilt.android") version "2.57.2" apply false
>>>>>>> 2adbb52ebf87dfede19d67b9789c30621982a91e
}