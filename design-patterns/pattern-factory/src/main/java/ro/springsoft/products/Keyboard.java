package ro.springsoft.products;

import java.io.File;

import ro.springsoft.Product;

/**
 * Author: Miroslav MARKO Date: 9/16/13
 */
public class Keyboard implements Product {
	private double price;
	private File image;
	private String description;

	public Keyboard() {
		description = "Keyboard";
		price = 5 + (Math.random() % 50);
	}

	public Keyboard(String description) {
		this();
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public File getImage() {
		return image;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Keyboard{" + "image=" + image + ", description='" + description
				+ '\'' + '}' + " $:" + getPrice();
	}
}
