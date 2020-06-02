package WingsMavenExp.WingsMavenexp.service.rest.properties

import WingsMavenExp.WingsMavenexp.service.util.PropertiesAccessor

import scala.collection.immutable._

trait PropertiesService {

  def content: Map[String, String]

}

object PropertiesService {

  def apply(accessors: PropertiesAccessor*): PropertiesService =
    new PropertiesService {
      override val content = accessors.flatMap(_.toMap).toMap
    }

}
