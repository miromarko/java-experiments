package ro.springsoft.products;

import java.io.File;

import ro.springsoft.Product;

/**
 * Author: Miroslav MARKO Date: 9/16/13
 */
public class Mouse implements Product {
	private double price;
	private File image;
	private String description;

	public Mouse() {
		price = (Math.random() % 50) + 5;
		description = "Mouse";
	}

	public Mouse(String description) {
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

	public String toString() {
		return "Mouse{" + "image=" + image + ", description='" + description
				+ '\'' + '}' + " $:" + getPrice();
	}
}
