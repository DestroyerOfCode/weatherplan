import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.20-RC"
    kotlin("plugin.spring") version "1.9.20-RC"

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
    implementation(project(":config"))

    //spring
    implementation(libs.spring.boot.starter.webflux)
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    //business logic
    implementation(libs.modelmapper)
    implementation(libs.org.yaml)
    implementation(libs.jackson.databind)

    //reactor
//    implementation(libs.reactor.kotlin.extensions)

    //logging
    implementation(libs.bundles.logging.bundle)

    //testing
    testImplementation(kotlin("test"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1")
//    testImplementation(libs.bundles.web.test.bundle)

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