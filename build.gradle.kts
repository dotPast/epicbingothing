plugins {
    kotlin("jvm") version "2.0.10"
    id("com.gradleup.shadow") version "8.3.2"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "hi.dottt"
version = "1.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.1-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation(files("libs/origami.engine.jar"))
    implementation("org.jetbrains.kotlin:kotlin-reflect:2.0.20")
    api("com.github.AsoDesu.Origami:bukkit:-SNAPSHOT")
    api("com.github.AsoDesu.Origami:commands:-SNAPSHOT")
    api("com.github.AsoDesu.Origami:common:-SNAPSHOT")
    api("com.github.AsoDesu.Origami:designs:-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}


tasks {
    runServer {
        minecraftVersion("1.21.1")
    }
}