plugins {
    id("lib-kotlin-android-no-config")
    id("publish-android")
}

dependencies {
    api(projects.appToastApi)

    testImplementation(libs.test.powermock.core)
    testImplementation(libs.test.powermock.junit4)
    testImplementation(libs.test.powermock.mockito)
}