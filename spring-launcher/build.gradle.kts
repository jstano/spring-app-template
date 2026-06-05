plugins {
  id("com.stano.java-module")
  id("com.stano.spring-boot")
}

dependencies {
  implementation(project(":adapter-rest-api"))
  implementation(project(":application-services"))
  implementation(project(":spring-configuration"))

  implementation("com.stano:platform-jackson")
  implementation("com.stano:platform-logging")
  implementation("com.stano:platform-spring-data")

  testImplementation("com.stano:platform-spring-data-test")
}
