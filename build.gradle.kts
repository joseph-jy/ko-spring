import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val mockkVersion = "1.13.4"
val restAssuredVersion = "5.3.2"
val wiremockVersion = "3.2.0"
val wiremockSpringBootVersion = "2.0.0"
val kotestVersion = "5.5.5"

plugins {
	id("org.springframework.boot") version "3.2.1"
	id("io.spring.dependency-management") version "1.1.4"
	id("com.coditory.integration-test") version "1.4.5"
	kotlin("jvm") version "1.9.20"
	kotlin("plugin.spring") version "1.9.20"
}

group = "io.joseph"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
	targetCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/milestone") }
	maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-undertow")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("mysql:mysql-connector-java:8.0.33")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-mysql")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.junit.jupiter:junit-jupiter-api")
	testImplementation("org.junit.jupiter:junit-jupiter-engine")
	testImplementation("org.wiremock:wiremock:$wiremockVersion")
	testImplementation("com.maciejwalkowiak.spring:wiremock-spring-boot:2.0.0")
	testImplementation("io.rest-assured:rest-assured:$restAssuredVersion")
	testImplementation("io.rest-assured:kotlin-extensions:$restAssuredVersion")
	testImplementation("io.rest-assured:spring-mock-mvc:$restAssuredVersion")
	testImplementation("io.mockk:mockk:$mockkVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = freeCompilerArgs + "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

java.sourceSets["main"].kotlin.srcDirs("src/main/kotlin")

sourceSets {
	create("integrationTest") {
		kotlin.srcDirs("src/integration/kotlin")
		resources.srcDirs("src/integration/resources")
		compileClasspath += sourceSets.main.get().output + sourceSets.test.get().output
		runtimeClasspath += sourceSets.main.get().output + sourceSets.test.get().output
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
