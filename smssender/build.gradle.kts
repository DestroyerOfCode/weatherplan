import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


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
    //dependent projects
    implementation(project(":config"))
    implementation(project(":homeweather"))

    //spring
    implementation(libs.spring.boot.starter)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    //kotlin
    api(libs.reactor.kotlin.extensions)

    //testing
    testImplementation(kotlin("test"))
    testImplementation(libs.bundles.web.test.reactive)
    testImplementation(libs.bundles.testcontainers)

    //3rd parties
    implementation(libs.twilio)
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

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        VersionNumber.version(21)
    }
}