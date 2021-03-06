/*
 * Copyright 2013 Blue Lotus Software, LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/* 
 * $Id$
 */
package ro.springsoft.jsf.el;

import com.google.inject.Injector;
import java.beans.FeatureDescriptor;
import java.util.Iterator;
import java.util.Map;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.FacesWrapper;
import javax.faces.context.FacesContext;

/**
 * A {@link FacesWrapper} implementation that wraps an {@link ELResolver} and
 * provides a mechanism to inject Guice {@link Injector} implementations.
 *
 * @author John Yeary <jyeary@springsoft.com>
 * @version 1.0
 */
public class GuiceELResolverWrapper extends ELResolver implements FacesWrapper<ELResolver> {

    private ELResolver wrapped;

    /**
     * Default constructor.
     */
    public GuiceELResolverWrapper() {
    }

    /**
     * Constructor that wraps an {@link ELResolver}.
     *
     * @param wrapped The resolver to wrap;
     */
    public GuiceELResolverWrapper(final ELResolver wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ELResolver getWrapped() {
        return wrapped;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This implementation checks to see if the property
     * can be resolved using the wrapped instance. If the property is resolved,
     * this method will inject the Guice {@link Injector} implementations.
     */
    @Override
    public Object getValue(final ELContext context, final Object base, final Object property) {

        Object obj = getWrapped().getValue(context, base, property);

        if (null != obj) {
            FacesContext fctx = (FacesContext) context.getContext(FacesContext.class);

            if (null != fctx) {

                Map map = fctx.getExternalContext().getApplicationMap();
                Injector injector = (Injector) map.get(Injector.class.getName());
                if (injector == null) {
                    throw new NullPointerException("Could not locate "
                            + "Guice Injector in application scope using"
                            + " key '" + Injector.class.getName() + "'");
                }
                injector.injectMembers(obj);
            }
        }

        return obj;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This returns the wrapped implementation.</p>
     */
    @Override
    public Class<?> getType(final ELContext context, final Object base, final Object property) {
        return getWrapped().getType(context, base, property);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This returns the wrapped implementation.</p>
     */
    @Override
    public void setValue(final ELContext context, final Object base, final Object property, final Object value) {
        getWrapped().setValue(context, base, property, value);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This returns the wrapped implementation.</p>
     */
    @Override
    public boolean isReadOnly(final ELContext context, final Object base, final Object property) {
        return getWrapped().isReadOnly(context, base, property);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This returns the wrapped implementation.</p>
     */
    @Override
    public Iterator<FeatureDescriptor> getFeatureDescriptors(final ELContext context, final Object base) {
        return getWrapped().getFeatureDescriptors(context, base);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong>Note:</strong> This returns the wrapped implementation.</p>
     */
    @Override
    public Class<?> getCommonPropertyType(final ELContext context, final Object base) {
        return getWrapped().getCommonPropertyType(context, base);
    }

}
