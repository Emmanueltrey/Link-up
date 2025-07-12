// build.gradle.kts
plugins {
    kotlin("jvm") version "1.9.22"  
    application  
    id("com.github.johnrengelman.shadow") version "8.1.1" // For Fat JAR
}

group = "com.chat.linkup"  
version = "1.0.0"  

repositories {  
    mavenCentral()  
}  

dependencies {  
    // Kotlin  
    implementation(kotlin("stdlib"))  

    // Ktor (Web server + WebSockets for chat)  
    implementation("io.ktor:ktor-server-core:2.3.5")  
    implementation("io.ktor:ktor-server-netty:2.3.5")  
    implementation("io.ktor:ktor-websockets:2.3.5")  

    // Logging  
    implementation("ch.qos.logback:logback-classic:1.4.11")  

    // Testing  
    testImplementation(kotlin("test"))  
}  

application {  
    mainClass.set("com.chat.linkup.MainKt")  
}  

tasks.shadowJar {  
    archiveBaseName.set("chat-link-up")  
    archiveClassifier.set("")  
    manifest {  
        attributes["Main-Class"] = application.mainClass.get()  
    }  
}  

tasks.test {  
    useJUnitPlatform()  
}
