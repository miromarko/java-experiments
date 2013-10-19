package ro.springsoft.guice.slf4j;

import com.google.inject.MembersInjector;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Code from the blog post based on the blog at <a
 * href="http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice">SLF4J
 * Logger Injection with Guice</a>.
 *
 * @author Dominik Obermaier
 */
public class SLF4JMembersInjector<T> implements MembersInjector<T> {

    private final Field field;
    private final Logger logger;

    public SLF4JMembersInjector(final Field field) {
        this.field = field;
        this.logger = LoggerFactory.getLogger(field.getDeclaringClass());
        field.setAccessible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void injectMembers(T instance) {
        try {
            field.set(instance, logger);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
