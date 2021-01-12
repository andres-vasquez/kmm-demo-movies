import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-android-extensions")
}

dependencies {
    val kotlin_version = "1.4.10"
    val navigationVersion = "2.3.2"

    // App dependencies
    val androidXVersion = "1.0.0"
    val androidXAnnotations = "1.0.1"
    val androidXLegacySupport = "1.0.0"
    val appCompatVersion = "1.2.0"
    val archLifecycleVersion = "2.2.0"
    val cardVersion = "1.0.0"
    val materialVersion = "1.2.1"
    val fragmentVersion = "1.3.0-beta01"
    val recyclerViewVersion = "1.1.0"
    val mockitoVersion = "2.23.0"
    val constraintVersion = "2.0.4"
    val dexMakerVersion = "2.12.1"
    val moshiVersion = "1.9.3"
    val coroutinesVersion = "1.3.9"
    val roomVersion = "2.2.5"
    val koinVersion = "2.0.1"
    val truthVersion = "1.0"
    val junitVersion = "4.13.1"
    val androidXTestCoreVersion = "1.3.0"
    val robolectricVersion = "4.3.1"
    val androidXTestExtKotlinRunnerVersion = "1.1.2"
    val archTestingVersion = "2.1.0"
    val hamcrestVersion = "1.3"
    val androidXTestRulesVersion = "1.3.1-alpha02"
    val espressoVersion = "3.3.0"

    implementation(project(":shared"))
    // App dependencies
    implementation("androidx.appcompat:appcompat:$appCompatVersion")
    implementation("androidx.legacy:legacy-support-v4:$androidXLegacySupport")
    implementation("androidx.annotation:annotation:$androidXAnnotations")
    implementation("com.android.support:multidex:1.0.3")

    implementation("androidx.cardview:cardview:$cardVersion")
    implementation("com.google.android.material:material:$materialVersion")
    implementation("androidx.recyclerview:recyclerview:$recyclerViewVersion")
    implementation("androidx.constraintlayout:constraintlayout:$constraintVersion")
    implementation("android.arch.work:work-runtime-ktx:1.0.1")

    // Architecture Components
    //Navigation dependencies
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version")
    kapt("androidx.lifecycle:lifecycle-compiler:$archLifecycleVersion")

    implementation("androidx.lifecycle:lifecycle-extensions:$archLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:$archLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-extensions:$archLifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$archLifecycleVersion")
    implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
    implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
    implementation("androidx.test.espresso:espresso-idling-resource:$espressoVersion")

    //glide
    implementation("com.github.bumptech.glide:glide:4.11.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.11.0")

    //Coroutines Dependencies
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutinesVersion")

    //Koin
    implementation("org.koin:koin-android:$koinVersion")
    implementation("org.koin:koin-androidx-viewmodel:$koinVersion")

    //Fragments
    implementation("androidx.fragment:fragment-ktx:$fragmentVersion")

    // Logging
    implementation("com.jakewharton.timber:timber:4.7.1")
}

android {
    compileSdkVersion(30)
    defaultConfig {
        applicationId = "com.github.andresvasquez.topmovies.androidApp"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        multiDexEnabled = true
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildFeatures {
        dataBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}