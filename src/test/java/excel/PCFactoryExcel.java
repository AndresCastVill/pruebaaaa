package excel;

public class PCFactoryExcel {

    private String nombreExcel = "ReportePCFactory";

    AccionesExcel excel = new AccionesExcel();

    public void excelPCFactory(String dato[]){
        if(!excel.existeExcel(nombreExcel)){
            String [] cabeceras = {"ID_Producto", "Marca Producto","Nombre Producto", "Precio Normal", "Precio Efectivo", "Cantidad", "URL Producto"};
            excel.crearExcel(nombreExcel,dato, cabeceras);

        }else{
            excel.agregarLineaExcel(nombreExcel,dato);
        }

    }

}
