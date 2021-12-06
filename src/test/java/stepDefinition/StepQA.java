package stepDefinition;

import db.ConsultasQA;
import driver.DriverContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.internal.bind.util.ISO8601Utils;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import page.*;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;
import util.Recursos;

import java.util.ArrayList;
import java.util.List;

public class StepQA {


    WebDriver driver = DriverContext.getDriver();
    String idProductoPO = "";
    Recursos recursos = new Recursos();
    ConsultasQA consultasQA = new ConsultasQA();


    QAInicio qaInicio = new QAInicio(driver);
    QAForm qaForm= new QAForm(driver);
    QABook qaBook= new QABook(driver);
    QABookDetalle qaBookDetalle= new QABookDetalle(driver);
    //PCFactoryInicioPO pcFactoryInicioPO = new PCFactoryInicioPO(driver);
    //PCFactoryProductoPO pcFactoryProductoPO = new PCFactoryProductoPO(driver);

    //PCFactoryCarritoPO carrito= new PCFactoryCarritoPO(driver);

    @And("llenar campos")
    public void llenadoCampos(DataTable table) throws InterruptedException {
        List<List<String>> rows = table.asLists();
        for (List<String> columns :rows) {
            qaForm.agregarMaterias(columns.get(0),columns.get(1));
        }
        qaForm.llenarOtros(consultasQA.consultaNombre());
        PdfReports.closePDF();

    }

    @And("validar detalle del libro")
    public void detalleLibro() {
        qaBookDetalle.verLibrosSeleccionados();
        //PdfReports.closePDF();

    }

    @And("cerrar sesion")
    public void cerrarSesion() {
        qaBookDetalle.cerrarSesion();
        PdfReports.closePDF();

    }

    @When("consultar libros")
    public void consultarLibros(DataTable table) throws InterruptedException {
        List<String> datos = new ArrayList<>();
        PdfReports.createPDF();
        qaInicio.apretarBotonBook();
        qaInicio.inicioSesion();
        List<List<String>> rows = table.asLists();
        int i=1;
        for (List<String> columns :rows) {
            qaBook.seleccionarLibros(columns.get(0));
            //qaBookDetalle.extraerIsbn(), qaBookDetalle.extraerTitle(), qaBookDetalle.extraerSubtitle(),qaBookDetalle.extraerAuthor(),qaBookDetalle.extraerPublisher(),qaBookDetalle.extraerTotal(), qaBookDetalle.extraerWebsite()
            //String isbn, String title, String subtitle, String author, String publisher, String total_page, String website
            //consultasQA.registrarLibro(qaBookDetalle.extraerIsbn(), qaBookDetalle.extraerTitle(), qaBookDetalle.extraerSubtitle(),qaBookDetalle.extraerAuthor(),qaBookDetalle.extraerPublisher(),qaBookDetalle.extraerTotal(), qaBookDetalle.extraerWebsite());
            datos=consultasQA.consultaDatosLibro(qaBookDetalle.extraerIsbn());
            recursos.guardarExcel(datos);
            PdfReports.addTextValidate("Validacion Libro "+i+" titulo", qaBookDetalle.extraerTitle(), datos.get(1).toString(), false);
            PdfReports.addTextValidate("Validacion Libro "+i+" subtitulo", qaBookDetalle.extraerSubtitle().replaceAll("'","-"), datos.get(2).toString(), false);
            PdfReports.addTextValidate("Validacion Libro "+i+" autor", qaBookDetalle.extraerAuthor(), datos.get(3).toString(), false);
            PdfReports.addTextValidate("Validacion Libro "+i+" publisher", qaBookDetalle.extraerPublisher().replaceAll("'","-"), datos.get(4).toString(), false);
            PdfReports.addTextValidate("Validacion Libro "+i+" total de hojas", qaBookDetalle.extraerTotal(), datos.get(5).toString(), false);
            PdfReports.addTextValidate("Validacion Libro "+i+" website", qaBookDetalle.extraerWebsite(), datos.get(6).toString(), false);
            i++;


            //for(int i=0;i<datos.size();i++){
              //  PdfReports.addTextValidate("Validacion"+datos.get(i).+" de libro en BD", idProductoPO, datosProductoPO.get(0).toString(), false);

            //}

            qaBookDetalle.agregarLibro();
            //qaBookDetalle.verLibrosSeleccionados();

        }
    }

    @When("ir a la pagina de llenado")
    public void irAPaginaALlenar() {
        System.out.println("Hola");
        PdfReports.createPDF();
        qaInicio.apretarBoton();
        qaForm.presionarOpcion();
        //List<List<String>> rows = table.asLists();
        //for (List<String> columns :rows) {
          //  qaForm.agregarMaterias(columns.get(0),columns.get(1));
        //}
        //qaForm.llenarOtros(consultasQA.consultaNombre());

    }
}
