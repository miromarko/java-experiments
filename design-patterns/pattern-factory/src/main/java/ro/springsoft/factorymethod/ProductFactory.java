package ro.springsoft.factorymethod;

import ro.springsoft.Product;
import ro.springsoft.products.Keyboard;
import ro.springsoft.products.Mouse;

/**
 * Method Factory
 * Author: Miroslav MARKO
 * Date: 9/16/13
 */
public class ProductFactory {
    //factory method
    public Product getKeyboard(){
        return new Keyboard();
    }

    //factory method
    public Product getMouse(){
        return new Mouse();
    }

    //simple factory
    public Product getProduct(String type){
        if(type.equals("mouse"))
            return getMouse();
        if(type.equals("keyboard"))
            return getKeyboard();
        return null;
    }

}
