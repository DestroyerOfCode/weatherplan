import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.0"
    kotlin("jvm") version "1.9.0"
}

group = "com.babkovic"
version = "1.0-SNAPSHOT"

apply(
    plugin = "org.springframework.boot"
)
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
    maven {
        setUrl("https://repo.spring.io/milestone")
    }

}

dependencies {
    //spring
//    implementation(libs.spring.boot.starter.data.mongodb.reactive)
    implementation(libs.spring.boot.starter.webflux)

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    //reactor
    implementation ("io.projectreactor.kotlin:reactor-kotlin-extensions:1.2.2")

    //logging
    implementation(libs.slf4j.api)

//    implementation("ch.qos.logback:logback-classic:1.4.11")
//    implementation(libs.slf4j.api)
    implementation(libs.springdoc.openapi.starter.webmvc.ui)

    //testing
    testImplementation(kotlin("test"))
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.mockito)
    testImplementation(libs.spring.boot.starter.test)
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
    testImplementation("io.projectreactor:reactor-test:3.5.10")

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