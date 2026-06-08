plugins {
  id("com.stano.java-module")
  id("com.stano.spring-boot")
}

dependencies {
  implementation(project(":adapter-rest-api"))
  implementation(project(":application-services"))
  implementation(project(":spring-configuration"))

  implementation("com.stano:msp-spring-boot-application")

  testImplementation("com.stano:msp-domain-jpa-test-starter")
}
