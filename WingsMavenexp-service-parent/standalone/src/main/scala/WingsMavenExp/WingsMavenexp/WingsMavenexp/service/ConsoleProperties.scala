package WingsMavenExp.WingsMavenexp.service

import WingsMavenExp.WingsMavenexp.service.util.PropertiesAccessor

class ConsoleProperties extends PropertiesAccessor {

  override val propertiesFileName: String = "console.properties"

  val serverUri = getProperty("console.server.uri")

}
