plugins {
  id("com.stano.java-module")
}

dependencies {
  api("com.stano:msp-domain-jpa-starter")
  api("com.stano:msp-types")

  testImplementation("com.stano:msp-domain-jpa-test-starter")
}
