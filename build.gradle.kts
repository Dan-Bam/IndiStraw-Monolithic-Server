import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.7.15"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	kotlin("plugin.jpa") version "1.6.21"
	kotlin("kapt") version "1.4.10"
}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

noArg {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}

group = "com.proejct.indistraw"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_11
}

repositories {
	mavenCentral()
}

dependencies {
	/* spring app */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	/* test */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.kotest:kotest-runner-junit5-jvm:4.4.3")
	testImplementation("io.kotest:kotest-assertions-core-jvm:4.4.3")
	implementation("io.kotest:kotest-extensions-spring:4.4.3")
	testImplementation("io.mockk:mockk:1.12.0")

	/* security */
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	/* jwt */
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	/** jpa **/
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("com.querydsl:querydsl-jpa:5.0.0")
	kapt("com.querydsl:querydsl-apt:5.0.0:jpa")

	/* DB */
	implementation("mysql:mysql-connector-java:8.0.32")
	implementation("org.springframework.boot:spring-boot-starter-data-redis")
	implementation("org.mariadb.jdbc:mariadb-java-client:3.1.2")

	/* validation */
	implementation("org.springframework.boot:spring-boot-starter-validation")

	/* logging */
	implementation("io.github.microutils:kotlin-logging-jvm:2.1.21")
	testImplementation("io.github.microutils:kotlin-logging-jvm:2.1.21")

	/* coolsms */
	implementation("net.nurigo:javaSDK:2.2")

	/* mapper */
	implementation("org.mapstruct:mapstruct:1.5.2.Final")
	kapt("org.mapstruct:mapstruct-processor:1.5.2.Final")

	/* boot pay */
	implementation("io.github.bootpay:backend:2.1.4")

	/* aws */
	implementation("org.springframework.cloud:spring-cloud-starter-aws:2.2.6.RELEASE")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
