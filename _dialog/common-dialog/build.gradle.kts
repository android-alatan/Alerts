plugins {
    id("lib-kotlin-android-no-config")
    id("lib-kotlin-robolectric")
    id("publish-android")
}

dependencies {
    api(libs.androidx.compat)
    api(libs.androidx.constraintlayout)
    api(libs.google.material)
    api(projects.commonDialogApi)
}