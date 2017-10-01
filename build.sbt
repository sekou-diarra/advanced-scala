name := "advance-scala"

version := "0.1"

scalaVersion := "2.12.3"

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
libraryDependencies ++=Seq("com.github.mpilquist" %% "simulacrum" % "0.11.0",
  "org.scalaz" % "scalaz-core_2.12" % "7.2.15"
)
