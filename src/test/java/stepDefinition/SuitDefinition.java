package stepDefinition;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import driver.DriverContext;
import reportPDF.PdfReports;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
import reportPDF.PdfReports;

public class SuitDefinition {

    @Before
    public static void config(){
        //PdfReports.createPDF();
    }

    @After
    public static void tearDown(){
        //PdfReports.closePDF();
        DriverContext.quitDriver();
    }

}
