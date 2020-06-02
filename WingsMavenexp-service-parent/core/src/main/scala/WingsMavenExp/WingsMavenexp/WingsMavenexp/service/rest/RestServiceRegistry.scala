package WingsMavenExp.WingsMavenexp.service.rest

import WingsMavenExp.WingsMavenexp.service.random.RandomService
import WingsMavenExp.WingsMavenexp.service.rest.metrics.MetricsService
import WingsMavenExp.WingsMavenexp.service.rest.properties.PropertiesService

case class RestServiceRegistry
(metricsService: MetricsService,
 propertiesService: PropertiesService,
 randomService: RandomService)
