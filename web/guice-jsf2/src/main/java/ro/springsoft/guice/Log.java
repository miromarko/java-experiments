package ro.springsoft.guice;

import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Scope;

/**
 *
 * Qualifier for a logger based on the blog at <a
 * href="http://forkbomb-blog.de/2012/slf4j-logger-injection-with-guice">SLF4J
 * Logger Injection with Guice</a>.
 *
 * @author Dominik Obermaier
 * @author John Yeary
 * @version 1.0
 */
@Scope
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD, TYPE})
public @interface Log {
}
