// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

buildscript {
    repositories {
        google()  // Repositório Google
        mavenCentral() // Repositório Maven Central
    }
    dependencies {
        // Classpath para o plugin do Google Services
        classpath("com.google.gms:google-services:4.3.15")  // Este plugin é essencial para o Firebase funcionar corretamente
    }
}
