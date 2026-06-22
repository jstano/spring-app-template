plugins {
  id("com.stano.java")
  id("com.stano.spring-boot")
}

dependencies {
  implementation(project(":spring-configuration"))

  implementation("com.stano:msp-schema-starter")
  implementation("com.stano:msp-spring-boot-application")

  testImplementation("com.stano:msp-domain-jpa-test-starter")
}
