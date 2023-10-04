plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.0"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.0"

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
    implementation(libs.spring.boot.starter.webflux)

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //logging
    implementation(libs.bundles.logging.bundle)

}
kotlin {
    jvmToolchain(17)
}