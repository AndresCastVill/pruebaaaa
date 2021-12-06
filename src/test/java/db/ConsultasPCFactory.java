package db;

import conexionBD.Conexion;
import reportPDF.EstadoPrueba;
import reportPDF.PdfReports;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConsultasPCFactory extends Conexion {

    private Connection conn = getConnection();

    public void registrarProducto(String id, String marca, String nombre, String precioNormal, String precioEfectivo, String cantidad, String url){

        try {
                Statement stm = conn.createStatement();
                stm.executeUpdate("INSERT INTO `producto`(`id_producto`, `marca_producto`, `nombre_producto`, `precio_normal`, `precio_efectivo`, `cantidad`, `URL`) VALUES ('"+id+"','"+marca+"','"+nombre+"','"+precioNormal+"','"+precioEfectivo+"','"+cantidad+"','"+url+"')");
                PdfReports.addReport("Registro de producto en BD", "Se valida que el producto nuevo se creo correctamente", EstadoPrueba.PASSED, false);

        }catch (SQLException throwables){
            throwables.printStackTrace();
            System.out.println("No existen registros");
        }
    }

    public String consultaIDProducto(String idProducto){
        String dato = "";
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT id_producto FROM producto WHERE id_producto = '"+idProducto+"'");

            while(rs.next()){
                dato = rs.getString("id_producto");
            }

            return dato;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }


    }

    public String consultaNombreProducto(String nombre){
        String dato = "";
        try{
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT nombre_producto FROM producto WHERE nombre_producto = '"+nombre+"'");

            while(rs.next()){
                dato = rs.getString("nombre_producto");
            }

            return dato;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }


    }

    public List<String> consultarProducto(String id){
        String idProductoBD = consultaIDProducto(id);
        try{
            List<String> datos = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM `producto` WHERE `id_producto` = '"+id+"'");

            while (rs.next()){
                    datos.add(rs.getString("id_producto"));
                    datos.add(rs.getString("marca_producto"));
                    datos.add(rs.getString("nombre_producto"));
                    datos.add(rs.getString("precio_normal"));
                    datos.add(rs.getString("precio_efectivo"));
                    datos.add(rs.getString("cantidad"));
                    datos.add(rs.getString("URL"));
                }

            return datos;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }


}
