package ro.springsoft.clicktest.guice;

import javax.inject.Inject;

import org.apache.click.Page;

public abstract class GuicePage extends Page{
	private static final long serialVersionUID = 1L;

	public GuicePage() {
		super();
		ClickGuiceListener.INJECTOR.injectMembers(this);
	}


}
