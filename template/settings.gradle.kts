rootProject.name = "{{ project_name }}"

pluginManagement {
  repositories {
    mavenLocal()
    gradlePluginPortal()
  }
}

plugins {
  id("com.stano.settings") version "0.1.15"
}

include("adapter-rest-api")
include("application-contracts")
include("application-services")
include("domain")
include("schema")
include("spring-configuration")
include("spring-launcher")
