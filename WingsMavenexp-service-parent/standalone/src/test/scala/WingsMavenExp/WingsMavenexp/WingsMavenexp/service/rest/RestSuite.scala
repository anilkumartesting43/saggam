package WingsMavenExp.WingsMavenexp.service.rest

import javax.ws.rs.client.ClientBuilder

import WingsMavenExp.WingsMavenexp.service.ConsoleApplication
import WingsMavenExp.WingsMavenexp.service.ConsoleProperties
import WingsMavenExp.WingsMavenexp.service.random.RandomService
import WingsMavenExp.WingsMavenexp.service.random.RandomServiceImpl
import WingsMavenExp.WingsMavenexp.service.rest.metrics.MetricsService
import WingsMavenExp.WingsMavenexp.service.rest.metrics.MetricsServiceConsoleImpl
import WingsMavenExp.WingsMavenexp.service.rest.properties.BuildProperties
import WingsMavenExp.WingsMavenexp.service.rest.properties.PropertiesService
import WingsMavenExp.WingsMavenexp.service.rest.v1.random.RandomResourceTest
import org.junit.AfterClass
import org.junit.BeforeClass
import org.junit.runner.RunWith
import org.junit.runners.Suite
import org.junit.runners.Suite.SuiteClasses

@RunWith(classOf[Suite])
@SuiteClasses(Array(
  classOf[ExceptionMapperImplTest],
  classOf[RandomResourceTest]))
class RestSuite

object RestSuite {

  // properties

  val buildProperties = new BuildProperties

  val consoleProperties = new ConsoleProperties

  // services

  val metricsService: MetricsService = new MetricsServiceConsoleImpl

  val propertiesService = PropertiesService(buildProperties, consoleProperties)

  val randomService: RandomService = new RandomServiceImpl

  // instantiate reset server

  val restServiceRegistry = new RestServiceRegistry(
    metricsService = metricsService,
    propertiesService = propertiesService,
    randomService = randomService)

  val restConfig = new RestConfigImpl(restServiceRegistry) {

    override def providerClasses = super.providerClasses + classOf[ExceptionMapperImplTest.ExceptionResource]

  }

  val restServerFactory = new RestServerFactoryJerseyImpl

  val restServer = ConsoleApplication.createServer(consoleProperties, restServerFactory, restConfig)

  val rootWebTarget = ClientBuilder.newClient.target(restServer.uri())

  @BeforeClass
  def startApplication(): Unit = restServer.start()

  @AfterClass
  def stopApplication(): Unit = restServer.stop()

}
