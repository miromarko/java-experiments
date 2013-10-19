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

import javax.faces.application.Application;
import javax.faces.application.ApplicationFactory;

/**
 * An implementation of {@link ApplicationFactory}.
 *
 * @author John Yeary <jyeary@springsoft.com>
 * @version 1.0
 */
public class FacesApplicationFactoryWrapper extends ApplicationFactory {

    private ApplicationFactory factory;

    /**
     * Constructor that wraps an {@link ApplicationFactory} instance.
     *
     * @param factory The factory instance to be wrapped.
     */
    public FacesApplicationFactoryWrapper(final ApplicationFactory factory) {
        this.factory = factory;
    }

    /**
     * {@inheritDoc}
     * <p>
     * This method returns a {@link FacesApplicationWrapper} instance.</p>
     */
    @Override
    public Application getApplication() {
        Application application = factory.getApplication();
        return new FacesApplicationWrapper(application);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplication(final Application application) {
        factory.setApplication(application);
    }

}
