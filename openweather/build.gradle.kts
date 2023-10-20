import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.20-RC"
//    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.20-RC"
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
//    implementation ("org.springframework.boot:spring-boot-starter-data-mongodb")

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
//    testImplementation( "org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-testcontainers")
    testImplementation ("org.testcontainers:junit-jupiter:1.19.1")

    testImplementation("org.testcontainers:testcontainers:1.19.1")
    testImplementation("org.testcontainers:mongodb:1.19.1")
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
