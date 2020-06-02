package WingsMavenExp.WingsMavenexp.service.rest.metrics

trait MetricsService {

  def count(identifier: String): Unit

  def measure(identifier: String, value: Long): Unit

}
