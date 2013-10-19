package ro.springsoft.clicktest.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;

public class ClickGuiceListener extends GuiceServletContextListener{
	public static Injector INJECTOR;

	@Override
	protected Injector getInjector() {
		ClickGuiceListener.INJECTOR = Guice.createInjector(Stage.PRODUCTION , new ServletModule(){
			@Override
			protected void configureServlets() {				
				install(new ClickModule());
				
			}
		});
		return ClickGuiceListener.INJECTOR;
	}

}
