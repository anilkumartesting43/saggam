package WingsMavenExp.WingsMavenexp.service

import WingsMavenExp.WingsMavenexp.service.random.RandomServiceImpl
import WingsMavenExp.WingsMavenexp.service.rest._
import WingsMavenExp.WingsMavenexp.service.rest.metrics.MetricsServiceConsoleImpl
import WingsMavenExp.WingsMavenexp.service.rest.properties.BuildProperties
import WingsMavenExp.WingsMavenexp.service.rest.properties.PropertiesService

object ConsoleApplication {

  def main(args: Array[String]): Unit = {

    // properties
    val consoleProperties = new ConsoleProperties
    val buildProperties = new BuildProperties
    val propertiesService = PropertiesService(consoleProperties, buildProperties)

    // instantiate rest server
    val restServerFactory = new RestServerFactoryJerseyImpl
    val restServiceRegistry = new RestServiceRegistry(
      metricsService = new MetricsServiceConsoleImpl,
      propertiesService = propertiesService,
      randomService = new RandomServiceImpl)
    val restConfig = new RestConfigImpl(restServiceRegistry)
    val restServer = createServer(consoleProperties, restServerFactory, restConfig)

    // start rest server
    try {
      restServer.start()
      restServer.join()
    } finally {
      restServer.stop()
    }

  }

  def createServer
  (consoleProperties: ConsoleProperties,
   restServerFactory: RestServerFactory,
   restConfig: RestConfig): RestServer =
    restServerFactory.create(
      uri = consoleProperties.serverUri,
      properties = restConfig.properties,
      resourceClasses = restConfig.providerClasses ++ restConfig.resourceClasses)

}
