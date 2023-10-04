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
    implementation(project(":config"))

    //spring
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
    testImplementation(libs.bundles.web.test.bundle)

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}