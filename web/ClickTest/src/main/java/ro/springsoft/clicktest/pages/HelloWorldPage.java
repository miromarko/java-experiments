package ro.springsoft.clicktest.pages;

import java.util.Date;

import ro.springsoft.clicktest.Greeter;
import ro.springsoft.clicktest.guice.GuicePage;

import com.google.inject.Inject;

/**
 * 
 * @author Miroslav MARKO <miromarko@gmail.com>
 */
public class HelloWorldPage extends GuicePage {
	private static final long serialVersionUID = -4114912028004824218L;

	@Inject
	private Greeter greeter;
	private Date time = new Date();
	

	public HelloWorldPage() {
		super();
		initPage();
	}




	protected void initPage() {
		addModel("time", time);
		addModel("hello", greeter.getGreet());

	}

}
