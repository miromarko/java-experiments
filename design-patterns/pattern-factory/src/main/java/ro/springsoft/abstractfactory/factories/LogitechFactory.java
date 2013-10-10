package ro.springsoft.abstractfactory.factories;

import ro.springsoft.Product;
import ro.springsoft.abstractfactory.AbstractFactory;
import ro.springsoft.products.Keyboard;
import ro.springsoft.products.Mouse;

/**
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public class LogitechFactory extends AbstractFactory{


    @Override
    public Product getMouse() {
        return new Mouse("Logitech Mouse");
    }

    @Override
    public Product getKeyboard() {
        return new Keyboard("Logitech Keyboard");
    }
}
