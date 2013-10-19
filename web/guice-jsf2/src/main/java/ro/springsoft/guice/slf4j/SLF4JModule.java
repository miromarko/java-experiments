package ro.springsoft.guice.slf4j;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * Based on the blog post based on the blog at <a
 * href="http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice">SLF4J
 * Logger Injection with Guice</a>, and <a
 * href="https://code.google.com/p/google-guice/wiki/CustomInjections">Custom
 * Injections</a>.
 *
 * @author Sam Berlin
 * @author Dominik Obermaier
 * @author John Yeary
 */
public class SLF4JModule extends AbstractModule {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure() {
        bindListener(Matchers.any(), new SLF4JTypeListener());
    }
}
