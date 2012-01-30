resolvers ++= Seq(
    DefaultMavenRepository,
    Resolver.url("Play", url("http://download.playframework.org/ivy-releases/"))(Resolver.ivyStylePatterns),
    "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"
)

libraryDependencies += "play" %% "play" % "2.0-beta"

resolvers += Classpaths.typesafeResolver
 
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse" % "1.5.0")