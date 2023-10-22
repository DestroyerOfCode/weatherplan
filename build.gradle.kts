import org.gradle.util.internal.VersionNumber
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version (libs.versions.org.springframework.boot) apply (false)
    kotlin("jvm") version libs.versions.org.jetbrains.kotlin
    kotlin("plugin.spring") version libs.versions.org.jetbrains.kotlin
}

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
    implementation(platform(libs.spring.boot.dependencies))
    implementation(libs.kotlin.gradle.plugin)
}

tasks.named<BootJar>("bootJar") {
    enabled = false
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
        VersionNumber.version(21)
    }
}
