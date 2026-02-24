plugins {
  `java-library`
  `maven-publish`
}

dependencies {
  api("org.apache.commons:commons-lang3:3.20.0")
  api(libs.slf4j.api)
}