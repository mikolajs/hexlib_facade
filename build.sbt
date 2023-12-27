enablePlugins(ScalaJSPlugin)

name := "hexlibJS"
scalaVersion := "3.2.0"
organizationName := "eu.brosbit"

//scalaJSUseMainModuleInitializer := true

resolvers += "HexLib"at "https://raw.githubusercontent.com/mikolajs/hexlib/repository/"

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.6.0"
libraryDependencies += "eu.brosbit" % "hexlib_3" % "0.2"
