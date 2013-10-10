package ro.springsoft.factorymethod;

import ro.springsoft.Product;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public class Client {
    public static void main(String... args) {
        ProductFactory factory = new ProductFactory();

        List<Product> basket = new LinkedList<Product>();

        basket.add(factory.getKeyboard());
        basket.add(factory.getMouse());

        //display basket
        for (Product p : basket)
            System.out.println(p.toString());
    }
}
