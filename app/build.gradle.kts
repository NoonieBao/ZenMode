plugins {
    id("com.android.application")
}

android {
    signingConfigs {
        getByName("debug") {
            storeFile = file("/home/zeon/android.key.jks")
            storePassword = "147258.as"
            keyPassword = "147258.as"
            keyAlias = "key0"
        }
    }
    namespace = "com.cppzeal.nightguard"
    compileSdk = 34

    defaultConfig {
        applicationId = "icu.insomnia.zen"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}






dependencies {
    compileOnly("de.robv.android.xposed:api:82") // 这里的版本号可能会有变化
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("com.google.code.gson:gson:2.8.8")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")


}