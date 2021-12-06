package page;

import driver.DriverContext;
import org.openqa.selenium.WebDriver;

public class Base {

    WebDriver driver;

    public Base(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver chromeConection(){
        driver = DriverContext.getDriver();
        return driver;
    }
}
