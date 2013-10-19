package ro.springsoft.guice.slf4j;

import ro.springsoft.guice.Log;
import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
import java.lang.reflect.Field;
import org.slf4j.Logger;

/**
 * <p>
 * This is from the Google Guice project documentation: <a
 * href="https://code.google.com/p/google-guice/wiki/CustomInjections">Custom
 * Injections</a> including the updates from Mark Bishop.</p>
 * <p>
 * Additionally, the blog post <a
 * href="http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice">SLF4J
 * Logger Injection with Guice</a> provided additional configuration
 * implementation details.</p>
 *
 * @author Sam Berlin
 * @author Mark Bishop
 * @author Dominik Obermaier
 * @author John Yeary
 */
public class SLF4JTypeListener implements TypeListener {

    /**
     * {@inheritDoc}
     */
    @Override
    public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
        for (Class<?> c = typeLiteral.getRawType(); c != Object.class; c = c.getSuperclass()) {
            for (Field field : c.getDeclaredFields()) {
                if (field.getType() == Logger.class && field.isAnnotationPresent(Log.class)) {
                    typeEncounter.register(new SLF4JMembersInjector<T>(field));
                }
            }
        }
    }
}
