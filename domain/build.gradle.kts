plugins {
  id("com.stano.java-module")
}

dependencies {
  api("com.stano:platform-spring-data")
  api("com.stano:platform-types")

  testImplementation("com.stano:platform-spring-data-test")
}
