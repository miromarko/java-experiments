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
package ro.springsoft.jsf.application;

import ro.springsoft.jsf.el.GuiceELResolverWrapper;

import javax.el.ELResolver;
import javax.faces.application.Application;
import javax.faces.application.ApplicationWrapper;

/**
 * An implementation of {@link ApplicationWrapper}.
 *
 * @author John Yeary <jyeary@springsoft.com>
 * @version 1.0
 */
public class FacesApplicationWrapper extends ApplicationWrapper {

    private Application wrapped;

    /**
     * Constructor that wraps an {@link Application} instance.
     *
     * @param wrapped The {@link Appplication} to be wrapped.
     */
    public FacesApplicationWrapper(final Application wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Application getWrapped() {
        return wrapped;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method returns a {@link ro.springsoft.jsf.el.GuiceELResolverWrapper} that wraps the
     * default {@link ELResolver}.
     */
    @Override
    public ELResolver getELResolver() {
        return new GuiceELResolverWrapper(getWrapped().getELResolver());
    }
}
