package ro.springsoft.jaxb.properties;

import java.util.logging.Logger;
import ro.springsoft.jaxb.properties.utils.AppConfig;

/**
 * Hello world!
 *
 */
public class App {
  private static final Logger LOG = Logger.getLogger(App.class.getName());

  public static void main(String[] args) {
    AppConfig appConfig = AppConfig.getInstance();
    LOG.info("***" + appConfig.get("mongoHostsTest"));
  }
  
}
