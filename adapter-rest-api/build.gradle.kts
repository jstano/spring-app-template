plugins {
  id("com.stano.java-module")
}

dependencies {
  implementation(project(":application-contracts"))

  implementation("com.stano:msp-rest-api-starter")
  implementation("com.stano:msp-spring-security-starter")

  testImplementation("com.stano:msp-rest-api-test-starter")
  testImplementation("com.stano:msp-spring-security-test-starter")
}
