object Versions {
    const val kotlinVersion = "1.5.20"
    const val roomVersion = "2.4.0-alpha03"
    const val coreKtxVersion = "1.5.0"
    const val appcompatVersion = "1.3.0"
    const val materialVersion = "1.3.0"
    const val constraintLayoutVersion = "2.0.4"
    const val junitVersion = "4.13.2"
    const val junitExtVersion = "1.1.2"
    const val espressoVersion = "3.3.0"
    const val dagger = "2.25.4"
    const val coroutinesVersion = "1.3.0"
    const val coroutinesPlayServices = "1.3.2"
    const val lifecycleVersion = "2.2.0-alpha03"
    const val retrofit2Version = "2.6.0"
    const val navComponents = "2.3.5"
    const val liveDataVersion = "2.2.0"
    const val timberVersion = "4.7.1"

}

object Dependencies {
    const val timber = "com.jakewharton.timber:timber:${Versions.timberVersion}"
    const val liveDataExt = "androidx.lifecycle:lifecycle-extensions:${Versions.liveDataVersion}"
    const val liveDataViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.liveDataVersion}"
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlinVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtxVersion}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appcompatVersion}"
    const val material = "com.google.android.material:material:${Versions.materialVersion}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayoutVersion}"
    const val junit = "junit:junit:${Versions.junitVersion}"
    const val junitExt = "androidx.test.ext:junit:${Versions.junitExtVersion}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espressoVersion}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.roomVersion}"
    const val ktCoRoutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutinesVersion}"
    const val ktCoRoutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutinesVersion}"
    const val ktCoRoutinesPlayServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Versions.coroutinesPlayServices}"

    //Retrofit & gson Deps
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit2Version}"
    const val retrofitGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit2Version}"

    const val navFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navComponents}"
    const val navRuntime = "androidx.navigation:navigation-runtime:${Versions.navComponents}"
    const val navUi = "androidx.navigation:navigation-ui-ktx:${Versions.navComponents}"
    const val navDynamic = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navComponents}"
}

object Kapts {

    const val roomCompiler = "androidx.room:room-compiler:${Versions.roomVersion}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"

}
