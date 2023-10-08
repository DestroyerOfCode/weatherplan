import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.20-Beta2"
//    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.20-Beta2"

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
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.0-M3"))
    //spring
    implementation(libs.spring.boot.starter.webflux)
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //logging
    implementation(libs.bundles.logging.bundle)

}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
    }
}

kotlin {
    jvmToolchain(21)
}