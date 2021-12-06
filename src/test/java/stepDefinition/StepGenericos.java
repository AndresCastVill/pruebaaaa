package stepDefinition;

import contants.Navegador;
import driver.DriverContext;
import io.cucumber.java.en.Given;
import org.openqa.selenium.WebDriver;

public class StepGenericos {


    //String url = "https://pcfactory.cl";
    String url = "https://demoqa.com";
    WebDriver driver = DriverContext.getDriver();

    @Given("Se inicializa navegador")
    public void seInicializaNavegador() {
        DriverContext.setUp(Navegador.Chrome, url);
    }
}
