package ro.springsoft.guice;

import ro.springsoft.guice.slf4j.SLF4JModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

/**
 * An implementation of {@link GuiceServletContextListener} that injects
 * {@link ro.springsoft.guice.slf4j.SLF4JModule}.
 *
 * @author John Yeary <jyeary@springsoft.com>
 * @version 1.0
 */
public class GuiceServletContextListenerImpl extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new SLF4JModule());
    }

}
