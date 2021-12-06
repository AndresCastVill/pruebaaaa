package page;

import db.ConsultasQA;
import driver.DriverContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;

import java.util.NoSuchElementException;

public class QABookDetalle extends Base {
    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public QABookDetalle(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath="/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[6]/div")
    WebElement profile;

    @FindBy(xpath="/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[6]/div/ul/li[3]")
    WebElement profile2;

    @FindBy(xpath = "//span[contains(.,'Profile')]//parent::li[contains(@class,'btn btn-light')]")
    WebElement profile3;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[1]/div")
    WebElement btn;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[1]/div[3]/button")
    WebElement logOut;






    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[1]/button")
    WebElement botonVolver;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[9]/div[2]/button")
    WebElement botonAgregar;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[1]/div[2]/label")
    WebElement txtIsbn;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[2]/div[2]/label")
    WebElement txtTitle;

    @FindBy(xpath = "//html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[3]/div[2]/label")
    WebElement txtSubtitle;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[4]/div[2]/label")
    WebElement txtAuthor;

    @FindBy(xpath = "//html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[5]/div[2]/label")
    WebElement txtPublisher;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[6]/div[2]/label")
    WebElement txtTotal;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/div[2]/div[8]/div[2]/label")
    WebElement txtWebsite;





    public String extraerIsbn(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtIsbn));
            dato = txtIsbn.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerTitle(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtTitle));
            dato = txtTitle.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerSubtitle(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtSubtitle));
            dato = txtSubtitle.getText();
            dato=dato.replaceAll("'", "-");
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }
    public String extraerAuthor(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtAuthor));
            dato = txtAuthor.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }
    public String extraerPublisher(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtPublisher));
            dato = txtPublisher.getText();
            dato=dato.replaceAll("'", "-");
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }
    public String extraerTotal(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtTotal));
            dato = txtTotal.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }
    public String extraerWebsite(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtWebsite));
            dato = txtWebsite.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }



    public void agregarLibro() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        wait.until(ExpectedConditions.visibilityOf(botonVolver));
        js.executeScript("arguments[0].scrollIntoView();", txtIsbn);
        PdfReports.addWebReportImage("Validar libro a ingresar", "Validar seleccion del libro", EstadoPrueba.PASSED,false);
        botonAgregar.sendKeys(Keys.ENTER);
        Thread.sleep(5000);
        driver.switchTo().alert().accept();
        //driver.switchTo().activeElement();
        //driver.switchTo().parentFrame();
        botonVolver.sendKeys(Keys.ENTER);
    }
    public void verLibrosSeleccionados(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //wait.until(ExpectedConditions.visibilityOf(profile));
        //profile.click();
        //wait.until(ExpectedConditions.visibilityOf(profile4));
        System.out.println(profile2.getText());
        System.out.println(profile3.getText());
        profile.click();
        //profile2.click();
        //profile3.click();
        //profile4.click();
        //js.executeScript("window.scrollBy(0,1000");
        wait.until(ExpectedConditions.visibilityOf(btn));
        js.executeScript("arguments[0].scrollIntoView();", btn);
        PdfReports.addWebReportImage("Validar todos los libros ingresados", "Validar seleccion de los 5 libros", EstadoPrueba.PASSED,false);


    }
    public void cerrarSesion(){
        wait.until(ExpectedConditions.visibilityOf(logOut));
        logOut.sendKeys(Keys.ENTER);

    }
}
