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
    //spring
    implementation(libs.springdoc.openapi.starter.webmvc.ui)
    implementation("org.springframework.cloud:spring-cloud-gateway:4.0.8")

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