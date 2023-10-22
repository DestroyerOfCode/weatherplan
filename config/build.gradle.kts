import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version (libs.versions.org.springframework.boot) apply (false)
    kotlin("jvm") version libs.versions.org.jetbrains.kotlin
    kotlin("plugin.spring") version libs.versions.org.jetbrains.kotlin
}

group = "com.babkovic"
version = "1.0-SNAPSHOT"

apply(
    plugin = "org.springframework.boot"
)

repositories {
    mavenCentral()
    maven {
        setUrl("https://repo.spring.io/milestone")
    }
}

dependencies {
    //spring
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.starter.data.mongodb.reactive)

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //logging
    implementation(libs.bundles.logging)
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

tasks.named<BootJar>("bootJar") {
    enabled = false
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        VersionNumber.version(21)
    }
}