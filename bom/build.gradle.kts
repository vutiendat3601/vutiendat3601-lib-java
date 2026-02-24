plugins {
  `java-platform`
  `maven-publish`
}

javaPlatform {
  allowDependencies()
}

dependencies {
  constraints {
    api("vn.io.vutiendat3601.lib:web:${project.version}")
    api("vn.io.vutiendat3601.lib:exception:${project.version}")
    api("vn.io.vutiendat3601.lib:util:${project.version}")
  }
}
