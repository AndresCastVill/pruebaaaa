package excel;

public class QAExcel {

    private String nombreExcel = "ReporteLibros";

    AccionesExcel excel = new AccionesExcel();

    public void excelQALibros(String dato[]){
        if(!excel.existeExcel(nombreExcel)){
            String [] cabeceras = {"IBNS", "TITLE","SUBTITLE", "AUTHOR", "PUBLISHER", "TOTAL PAGE", "URL Producto"};
            excel.crearExcel(nombreExcel,dato, cabeceras);

        }else{
            excel.agregarLineaExcel(nombreExcel,dato);
        }

    }

}
