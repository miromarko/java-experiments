package ro.springsoft.guice.web.config;

import com.google.inject.Binder;
import com.google.inject.Module;
import ro.springsoft.guice.web.rest.HelloResoruce;
import ro.springsoft.guice.web.services.Greeter;
import ro.springsoft.guice.web.services.impl.DefaultGreeter;

/**
 *
 * @author Miroslav MARKO
 */
public class RestModule implements Module{

  @Override
  public void configure(Binder binder) {
    binder.bind(Greeter.class).to(DefaultGreeter.class);
    
    //bind REST Services
    binder.bind(HelloResoruce.class);
  }
  
  
}
