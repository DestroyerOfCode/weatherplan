import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.1.3")
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("jvm") version "1.9.0"
}

group = "com.babkovic"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    //spring
    implementation(libs.spring.boot.starter.web) {
        exclude(group = "org.yaml", module = "snakeyaml")
    }

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)

    //logging
    implementation(libs.slf4j.api)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    //testing
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito)
    testImplementation(libs.spring.boot.starter.test)

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}