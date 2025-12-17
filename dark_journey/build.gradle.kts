plugins {
    kotlin("jvm") version "2.1.21" // or your Kotlin version
    id("com.adarshr.test-logger") version "4.0.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Kotest core + assertions
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-property:5.8.0") // optional for property testing
}

tasks.test {
    useJUnitPlatform() // Kotest runs on JUnit 5
}
