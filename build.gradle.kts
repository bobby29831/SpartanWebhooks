import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    kotlin("jvm") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    java
}

group = "net.ecoporium"
version = "2.0"

repositories {
    mavenCentral()
    mavenLocal()
    maven { url = uri("https://papermc.io/repo/repository/maven-public/") }
    maven { url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/") }
    maven { url = uri("https://repo.triumphteam.dev/snapshots/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.18.2-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.1")
    compileOnly(files("libs/SpartanAPI.jar"))

    // kotlin
    implementation(kotlin("stdlib-jdk8"))

    // night config
    implementation("com.electronwill.night-config:core:3.6.5")
    implementation("com.electronwill.night-config:toml:3.6.5")
    implementation("com.electronwill.night-config:json:3.6.5")

    // discord library
    implementation("club.minnced:discord-webhooks:0.8.0")

    // triumph commands
    implementation("dev.triumphteam:triumph-cmd-bukkit:2.0.0-SNAPSHOT")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    named<ShadowJar>("shadowJar") {
        archiveBaseName.set("SpartanWebhooks")
        archiveVersion.set(project.version as String)

        relocate("org.jetbrains.kotlin", "${project.group}.webhooks.kotlin")
        relocate("com.electronwill.night-config", "${project.group}.webhooks.nightconfig")
        relocate("club.minnced.discord.webhook", "${project.group}.webhooks.discord")
    }

    build {
        dependsOn(shadowJar)
    }
}

