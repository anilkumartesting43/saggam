package WingsMavenExp.WingsMavenexp.service.rest.properties

import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

import WingsMavenExp.WingsMavenexp.service.rest.RestServiceRegistryAware

@Path("/properties")
@Produces(Array(MediaType.APPLICATION_JSON))
class PropertiesResource extends RestServiceRegistryAware {

  @GET
  def properties(): Response = {
    val content = registry.propertiesService.content
    val representation = content.toArray.sorted.map { case (key, value) => s"$key = $value" }.mkString("\r\n")
    Response.ok(representation).build()
  }

}
