/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author Mao
 */
public class Alumns {

    private int id;
    private String name;
    private String secondLastName;
    private String OneLastName;
    private String address;
    private String email;
    private String password;
    private String telephone;
    private String rol;
    private String created;
    private String updated;

    public Alumns() {
    }

    public Alumns(int id, String name, String secondLastName, String OneLastName, String address, String email, String password, String telephone, String rol, String created, String updated) {
        this.id = id;
        this.name = name;
        this.secondLastName = secondLastName;
        this.OneLastName = OneLastName;
        this.address = address;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.rol = rol;
        this.created = created;
        this.updated = updated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getsecondLastName() {
        return secondLastName;
    }

    public void setsecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }

    public String getOneLastName() {
        return OneLastName;
    }

    public void setOneLastName(String OneLastName) {
        this.OneLastName = OneLastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
    
    
}
