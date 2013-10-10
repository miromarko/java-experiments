package ro.springsoft.abstractfactory;

import ro.springsoft.Product;
import ro.springsoft.abstractfactory.factories.LogitechFactory;
import ro.springsoft.abstractfactory.factories.MicrosoftFactory;

import java.util.LinkedList;
import java.util.List;

/**
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public class Client {
    public static void main(String... args) {
        String selectedManufacturer = "Microsoft";
        AbstractFactory factory = getFactory(selectedManufacturer);
        List<Product> basket = new LinkedList<Product>();

        basket.add(factory.getKeyboard());
        basket.add(factory.getMouse());


        //display basket
        for (Product p : basket)
            System.out.println(p.toString());

    }

    //simple factory pattern
    public static AbstractFactory getFactory(String type) {
        if (type.equals("Microsoft"))
            return new MicrosoftFactory();
        if (type.equals("Logitech"))
            return new LogitechFactory();
        return null;
    }
}
