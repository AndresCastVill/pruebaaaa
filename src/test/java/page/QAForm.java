package page;

import driver.DriverContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;

import java.util.List;
import java.util.NoSuchElementException;

public class QAForm  extends Base{
    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public QAForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[1]/div/div/div[2]/div/ul/li/span")
    WebElement btnFormulario;


    @FindBy(xpath = "/html/body/div[4]/div/div/div[2]/div/table/thead/tr/th[1]")
    WebElement btn;


    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[2]/input[1]")
    WebElement nombre;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[1]/div[4]/input[1]")
    WebElement apellido;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[4]/div[2]/input")
    WebElement telefono;


    @FindBy(id = "subjectsInput")
    WebElement materia;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[3]/div[2]/div[1]/label")
    WebElement radio1;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[7]/div[2]/div[1]/label")
    WebElement check1;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[7]/div[2]/div[2]/label")
    WebElement check2;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[2]/div[2]/input")
    WebElement correo;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[9]/div[2]/textarea")
    WebElement direccion;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[1]/div/input")
    WebElement nacimiento;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[10]/div[2]/div/div/div[1]/div[2]/div/input")
    WebElement estado;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[10]/div[3]/div/div/div[1]/div[2]/div/input")
    WebElement ciudad;

    @FindBy(xpath = "//div[contains(@id,'react-select')]")
    WebElement clickeable;


    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[11]/div/button")
    WebElement sub;

    @FindBy(xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[2]/div[1]/form[1]/div[5]/div[2]/div[2]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/select[1]/option")
    List<WebElement> month;

    @FindBy(xpath = "/html/body/div[2]/div/div/div[2]/div[2]/div[1]/form/div[5]/div[2]/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div[2]/select/option")
    List<WebElement> anio;

    @FindBy(xpath="//div[@class='react-datepicker__week']/div")
    List<WebElement> dias;














    public void presionarOpcion(){
        try {
            wait.until(ExpectedConditions.visibilityOf(btnFormulario));
            btnFormulario.click();
        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }
    public void llenarOtros(List<String> datos) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOf(nombre));
        nombre.sendKeys(datos.get(0));
        wait.until(ExpectedConditions.visibilityOf(apellido));
        apellido.sendKeys(datos.get(1));
        wait.until(ExpectedConditions.visibilityOf(direccion));
        direccion.sendKeys(datos.get(2));
        wait.until(ExpectedConditions.visibilityOf(correo));
        correo.sendKeys(datos.get(3));
        System.out.println(datos.get(4));
        if (datos.get(4).equals("Male")) {
            radio1.click();
        } else {
        }
        wait.until(ExpectedConditions.visibilityOf(telefono));
        telefono.sendKeys(datos.get(5));
        nacimiento.click();

        Thread.sleep(5000);

        String mes = datos.get(6).substring(3, 6);
        String dia = datos.get(6).substring(0, 2);
        String year = datos.get(6).substring(7, 11);

        System.out.println(mes + "-" + dia + "-" + year);
        for (int i = 0; i < month.size(); i++) {
            String mesAcortado = month.get(i).getText().substring(0, 3);
            System.out.println(mesAcortado + "-" + mes);
            if (mesAcortado.equals(mes)) {
                month.get(i).click();
                break;
            }

        }
        Thread.sleep(3000);
        for (int i = 0; i < anio.size(); i++) {
            if (anio.get(i).getText().equals(year)) {
                anio.get(i).click();
                break;
            }

        }
        Thread.sleep(3000);
        for (int i = 0; i < dias.size(); i++) {
            System.out.println(dias.get(i).getAttribute("class").toString());
            if (dias.get(i).getAttribute("class").toString().length() == 48 && dias.get(i).getText().equals("26")) {
                dias.get(i).click();
            }
        }


        wait.until(ExpectedConditions.visibilityOf(estado));
        estado.sendKeys(datos.get(7));
        wait.until(ExpectedConditions.visibilityOf(clickeable));
        clickeable.click();
        wait.until(ExpectedConditions.visibilityOf(ciudad));
        ciudad.sendKeys(datos.get(8));
        wait.until(ExpectedConditions.visibilityOf(clickeable));
        clickeable.click();

        wait.until(ExpectedConditions.visibilityOf(sub));
        nombre.click();
        PdfReports.addWebReportImage("Validar datos ingresados", "Validar data ingresada correctamente", EstadoPrueba.PASSED, false);
        Thread.sleep(5000);
        nombre.sendKeys(Keys.ENTER);
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", btn);
        PdfReports.addWebReportImage("Validar datos ingresados pantalla negra", "Validar data ingresada correctamente en iframe", EstadoPrueba.PASSED, false);
    }

    public void agregarMaterias(String materias,String otro){
        try {
            wait.until(ExpectedConditions.visibilityOf(materia));
            materia.sendKeys(materias);
            materia.sendKeys(Keys.ARROW_DOWN);
            materia.sendKeys(Keys.ENTER);

            if(otro.equals("Sports")){
               check1.click();

            }else if(otro.equals("Reading")){
                check2.click();

            }else{

            }



        } catch (NoSuchElementException | TimeoutException ignored) {
        }
    }






}
