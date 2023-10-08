import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.20-Beta2"
//    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.20-Beta2"
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
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.0-M3"))
    implementation(project(":homeweather"))
    implementation(project(":config"))

    //spring
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")

//    implementation(libs.spring.boot.starter.data.mongodb.reactive)
    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //reactor
    implementation(libs.reactor.kotlin.extensions)

    //logging
    implementation(libs.bundles.logging.bundle)

    //testing
    testImplementation(kotlin("test"))
//    testImplementation(libs.spring.boot.starter.test)
//    testImplementation(libs.spring.test)
//    testImplementation(libs.reactor.test)
    testApi(libs.bundles.web.test.bundle)

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "21"
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
