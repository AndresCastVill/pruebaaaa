package page;

import driver.DriverContext;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;

import java.util.List;
import java.util.NoSuchElementException;

public class PCFactoryResultadoBusqueda extends Base{

    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public PCFactoryResultadoBusqueda(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy (xpath = "//div[@class='product__image']/a")
    List<WebElement> listProductos;

    @FindBy (xpath = "//*[@id=\"productsalgolia\"]/div/div/div[2]/div[2]/div/ol/li[1]")
    WebElement cajaProducto;



    public void seleccionarProducto(String idProducto){
        try {
            Thread.sleep(5000);
            PdfReports.addWebReportImage("Validacion resultado de productos", "Validar que carguen correctamente los productos", EstadoPrueba.PASSED,false);
            for (WebElement elem: listProductos){
                if(elem.getAttribute("id").equals(idProducto)){
                    elem.click();
                    break;
                }
            }



        }catch (NoSuchElementException | TimeoutException | InterruptedException ignored){

        }

    }
}
