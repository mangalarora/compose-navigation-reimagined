plugins {
    `android-application-config`
    `kotlin-parcelize`
}

android {
    namespace = "${project.group}.reimagined.sample"
}

kotlin {
    compilerOptions {
        optIn.addAll(
            "androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi",
            "dev.olshevski.navigation.reimagined.ExperimentalReimaginedApi"
        )
    }
}

dependencies {
    implementation(project(":reimagined"))
    implementation(project(":reimagined-material"))
    implementation(Libs.AndroidX.Activity.Compose)
    implementation(Libs.AndroidX.Compose.Material)
    implementation(Libs.AndroidX.Compose.MaterialIconsExtended)
    debugImplementation(Libs.AndroidX.Compose.UiTooling)
    implementation(Libs.AndroidX.Compose.UiToolingPreview)
    implementation(Libs.AndroidX.Lifecycle.ViewModel.Compose)

    androidTestImplementation(project(":test-utils"))
    androidTestImplementation(Libs.AndroidX.Test.Runner)
    androidTestImplementation(Libs.AndroidX.Test.Espresso)
    androidTestImplementation(Libs.AndroidX.Compose.UiTestJunit4)
    androidTestImplementation(Libs.Google.Truth)
}