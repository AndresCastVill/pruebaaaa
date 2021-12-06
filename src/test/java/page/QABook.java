package page;

import driver.DriverContext;
import io.cucumber.datatable.DataTable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportPDF.PdfReports;

import java.util.List;

public class QABook extends Base {
    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public QABook(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }



    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement primerLibro;

    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement segundoLibro;

    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement tercerLibro;

    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement cuartoLibro;

    @FindBy(xpath = "//*[@id=\"see-book-Git Pocket Guide\"]/a")
    WebElement quintoLibro;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div[1]/div/input")
    WebElement inputBuscar;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/div/div/div/div/span/a")
    List<WebElement> listaLink;


    public void seleccionarLibros(String dato){
        wait.until(ExpectedConditions.visibilityOf(inputBuscar));
        inputBuscar.sendKeys(dato);
        int b=0;
        for(WebElement ret : listaLink){
            String nombre=ret.getText();
            if(dato.equals(nombre)){
                ret.click();
                break;
            }
            //PdfReports.addTextValidate("Validar nombre producto "+(b+1)+" ",bd.consultaNombreProducto(nombre),nombre,false);

            b++;
        }



    }
}
