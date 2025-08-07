plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("jacoco")
    id("org.jlleitschuh.gradle.ktlint")
}

android {
    namespace = "com.risadadobola"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.risadadobola"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }

    testOptions {
        unitTests.all {
            jacoco {
                // This block is deprecated, but keeping it for now to avoid breaking other configurations
                // if it's still needed by some older Gradle versions or plugins.
                // The actual configuration will be done in tasks.withType<Test>
            }
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    testImplementation(libs.junit)
    testImplementation("org.mockito:mockito-core:5.18.0")
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation("com.google.android.gms:play-services-ads:23.2.0")
}

ktlint {
    filter {
        exclude("**/Theme.kt")
    }
}

jacoco {
    toolVersion = "0.8.8"
}

tasks.withType<Test> {
    configure<JacocoTaskExtension> {
        excludes = listOf("com/android/**")
    }
}

tasks.register<JacocoReport>("jacocoTestReport") {
    dependsOn(tasks.withType<Test>())
    reports {
        xml.required.set(true)
        html.required.set(true)
    }

    classDirectories.setFrom(fileTree("$buildDir/intermediates/javac/debug/classes") + fileTree("$buildDir/tmp/kotlin-classes/debug"))

    sourceDirectories.setFrom(
        files(
            "src/main/java",
            "src/main/kotlin",
        ),
    )

    executionData.setFrom(
        fileTree(project.rootDir) {
            include("**/*.exec")
        },
    )
}
