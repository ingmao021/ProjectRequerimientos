/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Mao
 */
public class AdminDao {

    ConnetionMySQL ctn = new ConnetionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public static int admin_id = 0;
    public static String admin_fullname = "";
    public static String admin_telefono = "";
    public static String admin_correo = "";
    public static String admin_rol = "";


    /**
     * REQUERIMIENTO "Necesito un sistema de autenticacion (login) que permita
     * al secretario acceder al programa,una vez dentro del programa el
     * secretatio debe poder registrar la informacion de los alumnos"
     *
     * @param correo
     * @param contraseña
     * @return 
     *
     */
    //traer el correo y contraseña para hacer el ingreso
    public Admin LoginQuery(String correo, String contraseña) {
        String query = "SELECT * FROM administradores WHERE email = ? AND password = ?";
        Admin admin = new Admin();

        try {
            
            conn = ctn.getConnection();
            if (conn == null) {
                JOptionPane.showMessageDialog(null, "No se pudo conectar a la base de datos");
                return admin;
            }
            
            pst = conn.prepareStatement(query);
            pst.setString(1, correo);
            pst.setString(2, contraseña);
            rs = pst.executeQuery();

            if (rs.next()) {
                admin.setId(rs.getInt("id"));
                admin_id = admin.getId();
                
                admin.setName(rs.getString("fullname"));
                admin_fullname = admin.getName();
                
                admin.setTelefono(rs.getString("telephone"));
                
                admin.setCorreo(rs.getString("email"));
                admin_correo = admin.getCorreo();
                
                admin.setRol(rs.getString("rol"));
                admin_rol = admin.getRol();
        
                }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error login " + e.getMessage());
        }finally{
            try{
            if (pst != null) pst.close();           
            if (conn != null) conn.close();           
            if (rs != null) rs.close();           
                
            }catch(SQLException e){
                e.printStackTrace();
            }            
        }
        return admin;
    }

    /**registrar Administrador
    "INSERT INTO administradores( id, name, identification, telephone, email, password, rol, created, updated)"
                + "VALUES(?,?,?,?,?,?,?,?,?)"
    CON ESTA CONSULTA INGRESAMOS LA INFO PARA QUE INGRESE EL ENCARGADO DEL REGISTRO
    **/
}
