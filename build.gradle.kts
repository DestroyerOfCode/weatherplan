import org.gradle.util.internal.VersionNumber.version
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version ("3.2.0-M3")
    kotlin("jvm") version "1.9.20-RC"
//    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.20-RC"
}

apply(
    plugin = "org.springframework.boot"
)

repositories {
    mavenCentral()
    maven {
        setUrl("https://repo.spring.io/milestone")
    }
    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20-RC")
    }
}

dependencies {
    implementation(platform("org.springframework.boot:spring-boot-dependencies:3.2.0-M3"))
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20-RC")

}

tasks.test {
    useJUnitPlatform()
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

kotlin {
    jvmToolchain(21)
    compilerOptions {
        version(21)
    }
}
