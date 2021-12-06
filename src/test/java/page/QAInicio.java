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

public class QAInicio extends Base {
    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public QAInicio(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[2]")
    WebElement btnFormulario;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div/div[6]")
    WebElement btnBook;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/button")
    WebElement btnBook1;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[2]/div[2]/input")
    WebElement user;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[3]/div[2]/input")
    WebElement pass;

    String usuario="GerardoCastilloVill";
    String psw="Contra#123";


    public void apretarBoton() {

        try {
            wait.until(ExpectedConditions.visibilityOf(btnFormulario));
            PdfReports.addWebReportImage("Real pagina de inicio", "Inicio foto", EstadoPrueba.PASSED,false);
            btnFormulario.click();

        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }

    public void apretarBotonBook() {

        try {
            wait.until(ExpectedConditions.visibilityOf(btnBook));
            //PdfReports.addWebReportImage("Real pagina de inicio", "Inicio foto", EstadoPrueba.PASSED,false);
            btnBook.click();
            wait.until(ExpectedConditions.visibilityOf(btnBook1));
            btnBook1.click();

        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }

    public void inicioSesion() {

        try {
            wait.until(ExpectedConditions.visibilityOf(user));
            //PdfReports.addWebReportImage("Real pagina de inicio", "Inicio foto", EstadoPrueba.PASSED,false);
            user.sendKeys(usuario);
            wait.until(ExpectedConditions.visibilityOf(pass));
            pass.sendKeys(psw + Keys.ENTER);

        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }
}
