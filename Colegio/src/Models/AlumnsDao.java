/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Mao
 */
public class AlumnsDao {

    ConnetionMySQL ctn = new ConnetionMySQL();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    //realizamos el registro de un Alumno
    public boolean registerAlumnQuery(Alumns alumn) {
        String query = "INSERT INTO alumns(id,name,primerApellido,segundoApellido,address,"
                + "email,password,telephone,rol,created,updated)"
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";

        Timestamp datetime = new Timestamp(new Date().getTime());

        try {
            conn = ctn.getConnection();
            pst = conn.prepareStatement(query);

            pst.setInt(1, alumn.getId());
            pst.setString(2, alumn.getName());
            pst.setString(3, alumn.getOneLastName());
            pst.setString(4, alumn.getsecondLastName());
            pst.setString(5, alumn.getAddress());
            pst.setString(6, alumn.getEmail());
            pst.setString(7, alumn.getPassword());
            pst.setString(8, alumn.getTelephone());
            pst.setString(9, alumn.getRol());
            pst.setTimestamp(10, datetime);
            pst.setTimestamp(11, datetime);

            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
            return false;
        } finally{
            try{
                if(conn != null) conn.close();
                if(pst != null) pst.close();
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //modificar los datos de alumnos
    public boolean updateAlumnQuery(Alumns alumn) {
        String query = "UPDATE alumns SET name = ?, primerApellido = ?, segundoApellido = ?, address = ?, email = ?,"
                + "password = ?, telephone = ?, rol = ?, updated = ?"
                + "WHERE id = ? ";
        Timestamp datetime = new Timestamp(new Date().getTime());

        try {
            conn = ctn.getConnection();
            pst = conn.prepareStatement(query);

            pst.setString(1, alumn.getName());
            pst.setString(2, alumn.getOneLastName());
            pst.setString(3, alumn.getsecondLastName());
            pst.setString(4, alumn.getAddress());
            pst.setString(5, alumn.getEmail());
            pst.setString(6, alumn.getPassword());
            pst.setString(7, alumn.getTelephone());
            pst.setString(8, alumn.getRol());
            pst.setTimestamp(9, datetime);
            pst.setInt(10, alumn.getId());

            pst.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
            return false;
        }finally{
            try{
                if(conn != null) conn.close();
                if(pst != null) pst.close();
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //eliminar alumno
    public boolean deleteAlumnQuery(int id) {
        String query = "DELETE FROM alumns WHERE id = " + id;
        
        try{
            conn = ctn.getConnection();
            pst = conn.prepareStatement(query);
            pst.execute();
            return true;
        }catch(SQLException e){
            System.out.println("" + e.getMessage());
            return false;
        }finally{
            try{
                if(conn != null) conn.close();
                if(pst != null) pst.close();
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    //listar alumnos
    public List listAlumnsQuery(String id) {
        List<Alumns> lista_alumn = new ArrayList<>();

        String query = "SELECT * FROM alumns ORDER BY name ASC";
        String querySearch = "SELECT * FROM alumns WHERE id LIKE '%" + id + "%'";

        try {
            conn = ctn.getConnection();

            if (id.equalsIgnoreCase("")) {
                pst = conn.prepareStatement(query);
                rs = pst.executeQuery();
            } else {
                pst = conn.prepareStatement(querySearch);
                rs = pst.executeQuery();
            }

            while (rs.next()) {
                Alumns alumn = new Alumns();

                alumn.setId(rs.getInt("id"));
                alumn.setName(rs.getString("name"));
                alumn.setOneLastName(rs.getString("primerApellido"));
                alumn.setsecondLastName(rs.getString("segundoApellido"));
                alumn.setAddress(rs.getString("address"));
                alumn.setEmail(rs.getString("email"));
                alumn.setTelephone(rs.getString("telephone"));

                lista_alumn.add(alumn);
            }

        } catch (SQLException e) {
            System.out.println("" + e.getMessage());
        }finally{
            try{
                if(conn != null) conn.close();
                if(pst != null) pst.close();
                if(rs != null) rs.close();
                
            }catch(SQLException e){
                e.printStackTrace();
            }
        }
        return lista_alumn;
    }
}
