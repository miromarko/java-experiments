package ro.springsoft.guice.web.config;


import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.matcher.Matchers;

import ro.springsoft.guice.web.cache.Cached;
import ro.springsoft.guice.web.cache.GuiceCacheInterceptor;
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
    
	binder.bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cached.class),new GuiceCacheInterceptor());
  }
  
  
}
