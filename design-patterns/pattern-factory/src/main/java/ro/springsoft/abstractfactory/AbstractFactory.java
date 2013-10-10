package ro.springsoft.abstractfactory;

import ro.springsoft.Product;

/**
 * Abstract Factory
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public abstract class AbstractFactory {
    public abstract Product getMouse();
    public abstract Product getKeyboard();
}
