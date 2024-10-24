

lazy val root = (project in file("."))
  .settings(
    name := "dbx-scala-bundle-example"
  )

organization := "com.databricks"
mainClass    := Some("com.databricks.example.MainApp")

ThisBuild / scalaVersion  := "2.12.16"

Compile / packageBin / run / mainClass := Some("com.databricks.example.MainApp")
Compile / javacOptions ++= Seq("-source", "8")


val sparkVersion = "3.5.0"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"
)


assembly / assemblyMergeStrategy := {
  case PathList("META-INF", "services", "org.apache.spark.sql.sources.DataSourceRegister") => MergeStrategy.concat
  case PathList("META-INF", xs @ _*)                                                       => MergeStrategy.discard
  case x                                                                                   => MergeStrategy.first
}

Compile / assembly / artifact := {
  val art = (Compile / assembly / artifact).value
  art.withClassifier(Some("assembly"))
}

addArtifact(Compile / assembly / artifact, assembly)