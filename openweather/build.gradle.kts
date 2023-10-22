import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version (libs.versions.org.springframework.boot)
    kotlin("jvm") version libs.versions.org.jetbrains.kotlin
    kotlin("plugin.spring") version libs.versions.org.jetbrains.kotlin
    id("java-library")
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
    //dependent projects
    implementation(project(":homeweather"))
    implementation(project(":config"))

    //spring
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.spring.boot.starter.data.mongodb.reactive)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //reactor
    implementation(libs.reactor.kotlin.extensions)

    //logging
    implementation(libs.bundles.logging)

    //testing
    testImplementation(kotlin("test"))
    testApi(libs.bundles.web.test)

    testImplementation(libs.bundles.testcontainers)
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        VersionNumber.version(21)
    }
}

sourceSets {
    main {
        resources {
            setSrcDirs(listOf("src/main/resources"))
        }
    }
}

tasks.bootRun {
    environment = mapOf("appid" to project.findProperty("appid"))
}