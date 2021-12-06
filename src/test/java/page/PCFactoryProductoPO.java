package page;

import db.ConsultasPCFactory;
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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class PCFactoryProductoPO extends Base{

    WebDriver driver = DriverContext.getDriver();
    WebDriverWait wait = new WebDriverWait(driver, 30);

    public PCFactoryProductoPO(WebDriver driver) {
        super(driver);
        PageFactory.initElements(this.driver, this);
    }

    //@FindBy (xpath = "//body[1]/div[2]/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[2]/svg[1]")

    @FindBy (xpath = "/html/body/div[1]/div[4]/div/div[2]/div/div[3]/div[5]/div[2]/button")
    WebElement btnAnadirCarro;

    @FindBy (xpath = "//*[@id='pagar_ahora']")
    WebElement btnPagarAhora;

    @FindBy(xpath="/html/body/div[1]/div[4]/div/div[4]/div/div[2]/div[2]/div[2]/a[1]")
    WebElement btnSeguirComprando;

    @FindBy (xpath = "//*[@class='product-single-cart-modal']")
    WebElement ventanaModal;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[2]/div[2]/p[1]/span")
    WebElement txtIdProducto;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[2]/div[1]")
    WebElement txtNombreProducto;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[1]/div")
    WebElement txtMarcaProducto;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[3]/div[1]/div")
    WebElement txtPrecioEfectivo;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[3]/div[2]/div")
    WebElement txtPrecioNormal;

    @FindBy (xpath = "//*[@id='id_ficha_producto']/div[3]/div[2]/div[2]/p[2]")
    WebElement txtCantProducto;

    @FindBy (xpath = "//a[@id='seguir_comprando']")
    WebElement seguirComprando;

    @FindBy (xpath = "//span[contains(text(),'Mi carro')]")
    WebElement miCarro;

    //@FindBy (xpath = "//a[contains(text(),'Ir al carro')]")
    @FindBy (xpath = "/html/body/div[1]/div[3]/div/div[5]/ul/li/div[5]/button")
    WebElement Pagar;


    @FindBy (xpath = "//div[contains(text(),'$ 369.990')]")
    WebElement precio_carro1;

    @FindBy (xpath = "//div[@class='price-xl color-primary-1']")
    List<WebElement> listPrecio;

    @FindBy (xpath = "/html/body/div[1]/div[4]/div/div[2]/div/section[1]/div/div/article/div/header/div[2]")
    List<WebElement> listNombre;
    //@FindBy (xpath = "//div[@class='paragraph color-gray-1']")
    //List<WebElement> listNombre;


    public void anadirCarro(String dato){
        try {
            wait.until(ExpectedConditions.visibilityOf(btnAnadirCarro));
            PdfReports.addWebReportImage("Validar correcta seleccion del producto", "validar que se haya seleccionado producto correctamente", EstadoPrueba.PASSED,false);
            btnAnadirCarro.click();
            wait.until(ExpectedConditions.visibilityOf(btnSeguirComprando));
            btnSeguirComprando.click();
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }

    public void pagarAhora(){
        ConsultasPCFactory bd = new ConsultasPCFactory();
        try {
            wait.until(ExpectedConditions.visibilityOf(miCarro));
            miCarro.click();
            wait.until(ExpectedConditions.visibilityOf(Pagar));
            Pagar.click();

            int b=0;
            for(WebElement ret : listNombre){
                String nombre=ret.getText();
                PdfReports.addTextValidate("Validar nombre producto "+(b+1)+" ",bd.consultaNombreProducto(nombre),nombre,false);

                b++;
            }
            //wait.until(ExpectedConditions.visibilityOf(btnPagarAhora));
            //btnPagarAhora.click();
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }

    public void seguirComprando(){
        try {
            //wait.until(ExpectedConditions.visibilityOf(ventanaModal));
            wait.until(ExpectedConditions.visibilityOf(seguirComprando));
            seguirComprando.click();
        }catch (NoSuchElementException | TimeoutException ignored){

        }
    }

    public String extraerMarca(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtMarcaProducto));
            dato = txtMarcaProducto.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerNombreProducto(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtNombreProducto));
            dato = txtNombreProducto.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerIdProducto(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtIdProducto));
            dato = txtIdProducto.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerPrecioNormal(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtPrecioNormal));
            dato = txtPrecioNormal.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerPrecioEfectivo(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtPrecioEfectivo));
            dato = txtPrecioEfectivo.getText();
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public String extraerCantidad(){
        String dato ="";
        try{
            wait.until(ExpectedConditions.visibilityOf(txtCantProducto));
            dato = txtCantProducto.getText().substring(6,10);
        }catch(NoSuchElementException | TimeoutException ignored){

        }
        return dato;
    }

    public List<String> extraerDatosProducto(){
        List<String> listaDatos = new ArrayList<>();
        try{
            wait.until(ExpectedConditions.visibilityOf(txtMarcaProducto));

            listaDatos.add(extraerIdProducto());
            listaDatos.add(extraerMarca());
            listaDatos.add(extraerNombreProducto());
            listaDatos.add(extraerPrecioNormal());
            listaDatos.add(extraerPrecioEfectivo());
            listaDatos.add(extraerCantidad());
            listaDatos.add(driver.getCurrentUrl());

        }catch(NoSuchElementException | TimeoutException ignored){

        }

        return listaDatos;

    }





}
