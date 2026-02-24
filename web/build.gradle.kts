plugins {
  `java-library`
  `maven-publish`
}

dependencies {
  compileOnly(libs.jakarta.servlet.api)
}