pluginManagement {
    repositories {
        mavenCentral()
        maven {
            setUrl("https://repo.spring.io/milestone")
        }
        maven {
            setUrl("https://repo.spring.io/milestone")
        }

        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "Weatherplan"