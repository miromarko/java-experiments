package ro.springsoft.guice.web.services.impl;

import ro.springsoft.guice.web.services.Greeter;

/**
 *
 * @author Miroslav MARKO
 */
public class DefaultGreeter implements Greeter{

  @Override
  public String greet(String name) {
    return "Hello "+name;
  }
  
}
