
package ro.springsoft.clicktest.pages;

import java.util.Date;
import org.apache.click.Page;

/**
 *
 * @author Miroslav MARKO <miromarko@gmail.com>
 */
public class HelloWorld extends Page{
    private Date date = new Date();

    public HelloWorld() {
        addModel("time", date);
    }
    
    
}
