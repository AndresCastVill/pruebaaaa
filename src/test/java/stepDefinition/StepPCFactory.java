package stepDefinition;

import db.ConsultasPCFactory;
import driver.DriverContext;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import page.PCFactoryInicioPO;
import page.PCFactoryProductoPO;
import page.PCFactoryResultadoBusqueda;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;
import util.Recursos;

import java.util.ArrayList;
import java.util.List;

public class StepPCFactory {


    WebDriver driver = DriverContext.getDriver();
    String idProductoPO = "";
    Recursos recursos = new Recursos();
    ConsultasPCFactory consultasPCFactory = new ConsultasPCFactory();

    PCFactoryResultadoBusqueda pcFactoryResultadoBusqueda = new PCFactoryResultadoBusqueda(driver);
    PCFactoryInicioPO pcFactoryInicioPO = new PCFactoryInicioPO(driver);
    PCFactoryProductoPO pcFactoryProductoPO = new PCFactoryProductoPO(driver);

    @When("se agrega al carrito de compras")
    public void seAgregaAlCarritoDeCompras(DataTable table) {
        PdfReports.createPDF();
        pcFactoryInicioPO.traerInicio();
        List<List<String>> rows = table.asLists();
        for (List<String> columns :rows){
            pcFactoryInicioPO.buscarTipoProducto(columns.get(0));

            idProductoPO=columns.get(1);
            pcFactoryResultadoBusqueda.seleccionarProducto(idProductoPO);

            PCFactoryProductoPO pcFactoryProductoPO = new PCFactoryProductoPO(driver);
            List<String> datosProdPO = new ArrayList<>();

            List<String> datosProdBD = new ArrayList<>();


            datosProdPO=pcFactoryProductoPO.extraerDatosProducto();
            datosProdBD=consultasPCFactory.consultarProducto(idProductoPO);

            PdfReports.addTextValidate("Validacion id producto en BD", datosProdPO.get(0), datosProdBD.get(0), false);
            PdfReports.addTextValidate("Validacion marca_producto",datosProdPO.get(1), datosProdBD.get(1), false);
            PdfReports.addTextValidate("Validacion nombre_producto en BD", datosProdPO.get(2), datosProdBD.get(2), false);
            PdfReports.addTextValidate("Validacion nombre_producto en BD",datosProdPO.get(3), datosProdBD.get(3), false);
            PdfReports.addTextValidate("Validacion precio_normal en BD", datosProdPO.get(4), datosProdBD.get(4), false);
            PdfReports.addTextValidate("Validacion cantidad en BD", datosProdPO.get(5), datosProdBD.get(5), false);
            PdfReports.addTextValidate("Validacion URL en BD", datosProdPO.get(6), datosProdBD.get(6), false);


            recursos.guardarExcelPCFactory(pcFactoryProductoPO.extraerDatosProducto());

            pcFactoryProductoPO.anadirCarro(idProductoPO);

        }
    }

    @And("validar carrito")
    public void validarCarrito() {

        PCFactoryProductoPO pcFactoryProductoPO = new PCFactoryProductoPO(driver);

        pcFactoryProductoPO.pagarAhora();
        PdfReports.addWebReportImage("Validacion carrito", "Validar contenido carrito", EstadoPrueba.PASSED,false);

        PdfReports.closePDF();
    }
}
