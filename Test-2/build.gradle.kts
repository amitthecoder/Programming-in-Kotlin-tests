import buildutils.configureDetekt
import buildutils.createDetektTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import buildutils.configureDiktat
import buildutils.createDiktatTask

plugins {
    kotlin("jvm")
    id("com.github.gmazzo.buildconfig") version "3.0.3"
    kotlin("plugin.serialization")
}

group = "org.example"
version = "1.0-SNAPSHOT"

allprojects {
    apply {
        plugin("java")
        plugin("kotlin")
        plugin("kotlinx-serialization")
    }

    repositories {
        mavenCentral()
    }

    dependencies {
        val kotlinVersion = "1.7.20"
        implementation(kotlin("gradle-plugin", version = kotlinVersion))
        implementation(kotlin("serialization", version = kotlinVersion))
        implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.1")

        implementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
        runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
        implementation("org.junit.jupiter:junit-jupiter-params:5.9.0")
        runtimeOnly("org.junit.platform:junit-platform-console:1.9.0")
    }

    tasks.test {
        useJUnitPlatform()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    configureDiktat()
    configureDetekt()
}

createDiktatTask()
createDetektTask()