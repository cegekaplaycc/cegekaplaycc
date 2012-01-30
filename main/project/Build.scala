import sbt._
import Keys._
import PlayProject._

object ApplicationBuild extends Build {

    val appName         = "main"
    val appVersion      = "1.0"

    val appDependencies = Seq(
    	"org.easytesting" % "fest-assert" % "1.4" % "test"
      // Add your project dependencies here,
    )

    val main = PlayProject(appName, appVersion, appDependencies).settings(defaultJavaSettings:_*).settings(
      // Add your own project settings here      
    )

}
