plugins {
  `java-library`
}

description = "vutiendat3601 - Common Java modules"

allprojects {
  group = "vn.io.vutiendat3601.lib"
  version = "1.0.0"

  repositories {
    mavenLocal()
    mavenCentral()
    maven {
      name = "GitHubPackages"
      url = uri("https://maven.pkg.github.com/vutiendat3601/vutiendat3601-lib-java")

      credentials {
        username = project.findProperty("gpr.user") as String
        password = project.findProperty("gpr.key") as String
      }
    }
  }
}

subprojects {
  // The projects applied java-library plugin
  plugins.withId("java-library") {
    dependencies {
      compileOnly(libs.slf4j.api)
      compileOnly(libs.lombok)
      annotationProcessor(libs.lombok)
    }
    
    java {
      toolchain {
        languageVersion = JavaLanguageVersion.of(21)
      }
    }
    
    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
    }

    plugins.withId("maven-publish") {
      extensions.configure<PublishingExtension> {
        publications {
          create<MavenPublication>("maven") {
            from(components["java"])
            // groupId = project.group.toString()
            // artifactId = project.name.toString()
            // version = project.version.toString()
          }
        }
      }
    }
  }

  // The projects applied java-platform plugin
  plugins.withId("java-platform") { 
    plugins.withId("maven-publish") {
      extensions.configure<PublishingExtension> {
        publications {
          create<MavenPublication>("bom") {
            from(components["javaPlatform"])
            // groupId = project.group.toString()
            // artifactId = project.name.toString()
            // version = project.version.toString()
          }
        }
      }
    }
  }
}