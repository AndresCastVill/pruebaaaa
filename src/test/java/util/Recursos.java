package util;

import driver.DriverContext;
import excel.PCFactoryExcel;
import excel.QAExcel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Recursos {

    WebDriver driver = DriverContext.getDriver();

    public void guardarExcel(List<String> datos){
        QAExcel excel = new QAExcel();
        String[] datosExcel = new String[datos.size()];

        int i = 0;
        for(String info: datos){
            datosExcel[i] = info;
            i++;
        }
        excel.excelQALibros(datosExcel);
    }

    public void guardarExcelPCFactory(List<String> datos){
        PCFactoryExcel excel = new PCFactoryExcel();
        String[] datosExcel = new String[datos.size()];

        int i = 0;
        for(String info: datos){
            datosExcel[i] = info;
            i++;
        }
        excel.excelPCFactory(datosExcel);
    }

    public String extraerURL(){
        return driver.getCurrentUrl();
    }

}
