pluginManagement {
    repositories {
        mavenCentral()
        maven {
            setUrl("https://repo.spring.io/milestone")
        }
        maven {
            setUrl("https://repo1.maven.org/maven2/")
        }
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "weatherplan"
include("config")
include("openweather")
include("homeweather")
