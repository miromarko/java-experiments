package ro.springsoft.guice.web.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import ro.springsoft.guice.web.services.Greeter;

/**
 *
 * @author Miroslav MARKO
 */
@Path("hello")
public class HelloResoruce {
  private final Greeter greeter;

  @Inject
  public HelloResoruce(Greeter greeter) {
    this.greeter = greeter;
  }
  
  @GET
  @Path("{name}") 
  public String hello(@PathParam("name") final String name){
    return greeter.greet(name);
  }
    
}
