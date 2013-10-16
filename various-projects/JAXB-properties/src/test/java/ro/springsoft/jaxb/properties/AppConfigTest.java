package ro.springsoft.jaxb.properties;

import java.util.logging.Logger;
import junit.framework.TestCase;
import org.junit.Test;
import ro.springsoft.jaxb.properties.utils.AppConfig;

/**
 *
 * @author Miroslav MARKO
 */
public class AppConfigTest extends TestCase {

  private static final Logger LOG = Logger.getLogger(AppConfigTest.class.getName());

  @Test
  public void testGetConfig() {
    AppConfig appConfig = AppConfig.getInstance();
    LOG.info("***" + appConfig.get("mongoHostsTest"));
    assertNotNull(appConfig.get("mongoHostsTest"));
  }
}
