package ro.springsoft.guice.web;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Miroslav MARKO
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new java.util.HashSet<Class<?>>();
    addRestResourceClasses(resources);
    return resources;
  }

  /**
   * Do not modify addRestResourceClasses() method.
   * It is automatically re-generated by NetBeans REST support to populate
   * given list with all resources defined in the project.
   */
  private void addRestResourceClasses(Set<Class<?>> resources) {
    resources.add(ro.springsoft.guice.web.GenericResource.class);
  }
  
}
