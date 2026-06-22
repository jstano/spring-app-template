plugins {
  id("com.stano.java")
}

dependencies {
  api("com.stano:msp-domain-jpa-starter")

  testImplementation(project(":schema"))
  testImplementation("com.stano:msp-domain-jpa-test-starter")
}
