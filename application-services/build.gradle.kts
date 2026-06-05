plugins {
  id("com.stano.java-module")
}

dependencies {
  implementation(project(":application-contracts"))
  implementation(project(":domain"))

  implementation("com.stano:platform-application-services")
  implementation("com.stano:platform-spring-boot-application")
  implementation("org.springframework:spring-context")
  implementation("org.springframework:spring-tx")
}
