package ro.springsoft.abstractfactory.factories;

import ro.springsoft.Product;
import ro.springsoft.abstractfactory.AbstractFactory;
import ro.springsoft.products.Keyboard;
import ro.springsoft.products.Mouse;

/**
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public class MicrosoftFactory extends AbstractFactory{
    @Override
    public Product getMouse() {
        return new Mouse("Microsoft Mouse");
    }

    @Override
    public Product getKeyboard() {
        return new Keyboard("Microsoft Keyboard");
    }
}
