plugins {
  id("com.stano.java")
  id("com.stano.spring-boot")
}

dependencies {
  implementation(project(":adapter-rest-api"))
  implementation(project(":application-services"))
  implementation(project(":domain"))
  implementation(project(":spring-configuration"))

  implementation("com.stano:msp-spring-boot-application")
  implementation("com.stano:msp-spring-security-starter")

  testImplementation(project(":schema"))
  testImplementation("com.stano:msp-domain-jpa-test-starter")
  testImplementation("com.stano:msp-spring-security-test-starter")
}
