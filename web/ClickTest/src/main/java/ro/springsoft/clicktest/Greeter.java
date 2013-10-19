package ro.springsoft.clicktest;

import java.io.Serializable;


public class Greeter implements Serializable{
	private static final long serialVersionUID = 1L;
	private String hello = "Hello";
	
	public String getGreet(){
		return hello;
	}
}
