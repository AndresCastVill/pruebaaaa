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

public class ConsultasQA extends Conexion {

    private Connection conn = getConnection();


    public List<String> consultaNombre(){
        //String idProductoBD = consultaIDProducto(id);
        try{
            List<String> datos = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM `persona`");

            while (rs.next()){
                datos.add(rs.getString("nombre"));
                datos.add(rs.getString("apellido"));
                datos.add(rs.getString("direccion"));
                datos.add(rs.getString("correo"));
                datos.add(rs.getString("genero"));
                datos.add(rs.getString("telefono"));
                datos.add(rs.getString("nacimiento"));
                datos.add(rs.getString("estado"));
                datos.add(rs.getString("ciudad"));
            }

            return datos;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }

    public void registrarLibro(String isbn, String title, String subtitle, String author, String publisher, String total_page, String website){

        try {
            Statement stm = conn.createStatement();
            stm.executeUpdate("INSERT INTO libro (isbn,title,subtitle,author,publisher,total_page, website) VALUES ('"+isbn+"','"+title+"','"+subtitle+"','"+author+"','"+publisher+"','"+total_page+"','"+website+"')");
            //PdfReports.addReport("Registro de libro en BD", "Se valida que el libro nuevo se creo correctamente", EstadoPrueba.PASSED, false);

        }catch (SQLException throwables){
            throwables.printStackTrace();
            System.out.println("No existen registros");
        }
    }

    public List<String> consultaDatosLibro(String isbn){
        //String idProductoBD = consultaIDProducto(id);
        try{
            List<String> datos = new ArrayList<>();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM libro WHERE isbn = '"+isbn+"'");

            while (rs.next()){
                datos.add(rs.getString("isbn"));
                datos.add(rs.getString("title"));
                datos.add(rs.getString("subtitle"));
                datos.add(rs.getString("author"));
                datos.add(rs.getString("publisher"));
                datos.add(rs.getString("total_page"));
                datos.add(rs.getString("website"));
            }

            return datos;
        }catch (SQLException throwables){
            throwables.printStackTrace();
            return null;
        }
    }


}

