plugins {
    id("lib-kotlin-android-no-config")
    id("publish-android")
}

android {
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
}

dependencies {
    api(libs.compose.runtime)
    api(libs.compose.ui)
    api(libs.compose.material)
    compileOnly(libs.common.androidAnnotation)
    api(projects.composeDialogApi)
}