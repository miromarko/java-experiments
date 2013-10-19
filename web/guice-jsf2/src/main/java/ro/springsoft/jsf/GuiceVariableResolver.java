package ro.springsoft.jsf;

import com.google.inject.Injector;
import java.util.Map;
import javax.el.ELResolver;
import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.VariableResolver;

/**
 * This class is from the blog <a
 * href="http://notdennisbyrne.blogspot.com/2007/09/integrating-guice-and-jsf.html">Integrating
 * Guice and JSF</a>. It provides a {@link VariableResolver} which will work in
 * JSF 2, but is deprecated in preference to an {@link ELResolver}.
 *
 * @author Dennis Byrne
 * @see ro.springsoft.jsf.el.GuiceELResolverWrapper }
 */
@SuppressWarnings("deprecation")
public class GuiceVariableResolver extends VariableResolver {

    private final VariableResolver wrapped;

    public GuiceVariableResolver(VariableResolver wrapped) {
        if (wrapped == null) {
            throw new NullPointerException("wrapped "
                    + VariableResolver.class.getName());
        }
        this.wrapped = wrapped;
    }

    @Override
    public Object resolveVariable(FacesContext fctx, String name)
            throws EvaluationException {
        Object resolved = wrapped.resolveVariable(fctx, name);
        if (resolved != null) {
            Map map = fctx.getExternalContext().getApplicationMap();
            Injector injector = (Injector) map.get(Injector.class.getName());
            if (injector == null) {
                throw new NullPointerException("Could not locate "
                        + "Guice Injector in application scope using"
                        + " key '" + Injector.class.getName() + "'");
            }
            injector.injectMembers(resolved);
        }
        return resolved;
    }
}
