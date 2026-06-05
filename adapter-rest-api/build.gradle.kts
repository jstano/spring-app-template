plugins {
  id("com.stano.java-module")
}

dependencies {
  implementation(project(":application-contracts"))

  implementation("com.stano:platform-spring-webmvc")
  implementation("org.springframework.security:spring-security-core")
  implementation("org.springframework.security:spring-security-oauth2-core")
  implementation("org.springframework.boot:spring-boot-starter-validation")
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.8.9")
  implementation("io.swagger.core.v3:swagger-annotations:2.2.32")
  implementation("io.swagger:swagger-annotations:1.6.16")

  testImplementation("com.stano:platform-spring-test")
  testImplementation("org.springframework.boot:spring-boot-test")
  testImplementation("org.springframework.boot:spring-boot-test-autoconfigure")
  testImplementation("org.springframework.security:spring-security-test")
}
