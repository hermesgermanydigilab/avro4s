// Note: settings common to all subprojects are defined in project/GlobalPlugin.scala

// The root project is implicit, so we don't have to define it.
// We do need to prevent publishing for it, though:

val bintraySettings = Seq(
  publishMavenStyle := true,
  bintrayOrganization := None,
  bintrayRepository := "maven",
  bintrayOmitLicense := true,
  bintrayReleaseOnPublish := false
)

lazy val root = Project("avro4s_avro182", file("."))
  .settings(
    publish := {},
    publishArtifact := false,
    name := "avro4s"
  )
  .aggregate(
    `avro4s_avro182-core`,
    `avro4s_avro182-json`,
   // `avro4s-cats`,
    `avro4s_avro182-kafka`,
    `avro4s_avro182-refined`
  )

val `avro4s_avro182-core` = project.in(file("avro4s-core"))
  .settings(
    libraryDependencies ++= Seq(
      "com.propensive" %% "magnolia" % MagnoliaVersion,
      "com.chuusai" %% "shapeless" % ShapelessVersion,
      "org.json4s" %% "json4s-native" % Json4sVersion
    )
  ).settings(bintraySettings: _*)

val `avro4s_avro182-json` = project.in(file("avro4s-json"))
  .dependsOn(`avro4s_avro182-core`)
  .settings(
    libraryDependencies ++= Seq(
      "org.json4s" %% "json4s-native" % Json4sVersion
    )
  ).settings(bintraySettings: _*)

val `avro4s_avro182-cats` = project.in(file("avro4s-cats"))
  .dependsOn(`avro4s_avro182-core`)
  .settings(
    libraryDependencies ++= Seq(
      "org.typelevel" %% "cats-core" % CatsVersion
    )
  ).settings(bintraySettings: _*)

val `avro4s_avro182-kafka` = project.in(file("avro4s-kafka"))
  .dependsOn(`avro4s_avro182-core`)
  .settings(
    libraryDependencies ++= Seq(
      "org.apache.kafka" % "kafka-clients" % "2.4.0"
    )
  ).settings(bintraySettings: _*)

val `avro4s_avro182-refined` = project.in(file("avro4s-refined"))
  .dependsOn(`avro4s_avro182-core`)
  .settings(
    libraryDependencies ++= Seq(
      "eu.timepit" %% "refined" % RefinedVersion
    )
  ).settings(bintraySettings: _*)

publishMavenStyle in ThisBuild := true
bintrayOrganization in ThisBuild := None
bintrayRepository in ThisBuild := "maven"
bintrayOmitLicense in ThisBuild := true
bintrayReleaseOnPublish in ThisBuild := false
