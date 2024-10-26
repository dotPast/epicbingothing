plugins {
    kotlin("jvm") version "2.0.10"
    kotlin("plugin.serialization") version "2.0.21"
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
    maven("https://maven.noxcrew.com/public")
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

    implementation("com.noxcrew.interfaces:interfaces:1.3.1-SNAPSHOT")

    implementation("net.kyori:adventure-text-serializer-plain:4.17.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
}

kotlin {
    jvmToolchain(21)
}


tasks {
    runServer {
        minecraftVersion("1.21.1")
    }
}