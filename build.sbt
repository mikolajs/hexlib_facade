enablePlugins(ScalaJSPlugin)

val scala3Version = "3.2.0"

//scalaJSUseMainModuleInitializer := true

resolvers += "HexLib"at "https://raw.githubusercontent.com/mikolajs/hexlib/repository/"

lazy val root = project.
  in(file("."))
  .settings(
    name 	    := "hexlib_facade",
    organization    := "eu.brosbit",
    scalaVersion    := scala3Version,
    version := "0.2" ,
    libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "2.8.0",
    "eu.brosbit" % "hexlib_3" % "0.2"
    )
  )


