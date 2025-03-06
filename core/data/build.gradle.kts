plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.seller_app.core.data"

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.database)
    implementation(projects.core.network)
    implementation(projects.core.datastore)

    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    implementation(libs.work.manager)
    implementation(libs.hilt.work)

    implementation(libs.retrofit)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}