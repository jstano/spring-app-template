plugins {
  id("com.stano.java-module")
}

dependencies {
  implementation(project(":application-contracts"))
  implementation(project(":domain"))

  implementation("com.stano:msp-application-services-starter")

  testImplementation("com.stano:msp-spring-test-starter")
}
