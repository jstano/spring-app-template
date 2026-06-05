rootProject.name = "spring-app"

buildscript {
   val properties = extensions.extraProperties.properties
   val stanoMavenUrl = properties["com.stano.maven.url"]?.toString() ?: System.getenv("STANO_MAVEN_URL")
   val stanoMavenUsername = properties["com.stano.maven.username"]?.toString() ?: System.getenv("STANO_MAVEN_USERNAME")
   val stanoMavenPassword = properties["com.stano.maven.password"]?.toString() ?: System.getenv("STANO_MAVEN_PASSWORD")

   repositories {
      mavenLocal()
      mavenCentral()
      gradlePluginPortal()
      maven {
         name = "stano-maven"
         url = uri(stanoMavenUrl)
         credentials(PasswordCredentials::class) {
            username = stanoMavenUsername
            password = stanoMavenPassword
         }
      }
   }

   dependencies {
      classpath("com.stano:gradle-plugins-bom:${properties["gradlePluginsVersion"]}")
      classpath("com.stano:platform-spring-boot-gradle-plugin:${properties["javaPlatformVersion"]}")
   }
}

apply {
   plugin("com.stano.settings")
}

include("adapter-rest-api")
include("application-contracts")
include("application-services")
include("domain")
include("spring-configuration")
include("spring-launcher")
