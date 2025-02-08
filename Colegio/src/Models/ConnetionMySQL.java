/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Mao
 */
public class ConnetionMySQL {
   private String database = "colegio";
   private String user = "root";
   private String password = "*Root123";
   private String url = "jdbc:mysql://localhost:3306/" + database;
   
   public Connection getConnection(){
       Connection conn = null;
       try{
           //obtener el valor del drive
           Class.forName("com.mysql.cj.jdbc.Driver");
           conn = DriverManager.getConnection(url,user,password);
           
       }catch(ClassNotFoundException e){
           System.out.println("Error "  + e.getMessage());
           
       }catch(SQLException e){
           System.out.println("Erro al conectarse a la base de datos " + e.getMessage());
       }
       return conn;
   }
   
}
