package page;

import driver.DriverContext;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;

import java.util.NoSuchElementException;

public class PCFactoryInicioPO extends Base{

    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public PCFactoryInicioPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath="//*[@id='searchalgolia']/div/div/div/div/div/form/div/input")
    WebElement inputBuscar;

    @FindBy(xpath="//body/div[@id='app']/div[4]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/a[1]/img[1]")
    WebElement esperar;

    public void traerInicio(){
        try{
            wait.until(ExpectedConditions.visibilityOf(esperar));
            PdfReports.addWebReportImage("Real pagina de inico", "Inicio foto", EstadoPrueba.PASSED,false);
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }


    public void buscarTipoProducto(String tipoProducto){
        try{
            wait.until(ExpectedConditions.visibilityOf(inputBuscar));
            inputBuscar.sendKeys(tipoProducto+ Keys.ENTER);
            //PdfReports.addWebReportImage("Busqueda primera", "Busqueda primera", EstadoPrueba.PASSED,false);
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }

    public void buscarTipoProd(String tipoProducto){
        try{
            wait.until(ExpectedConditions.visibilityOf(inputBuscar));
            inputBuscar.sendKeys(tipoProducto+ Keys.ENTER);
            PdfReports.addWebReportImage("Pagina de Inicio", "Rescatar pagina de Inicio", EstadoPrueba.PASSED,false);
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }

}
