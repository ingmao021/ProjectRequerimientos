/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;

import Models.Admin;
import Models.AdminDao;
import Views.Login_section;
import Views.System_section;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Mao
 */
public class LoginControllers implements ActionListener, KeyListener {

    private Admin admin;
    private AdminDao admin_dao;
    private Login_section views;

    public LoginControllers(Admin admin, AdminDao admin_dao, Login_section views) {
        this.admin = admin;
        this.admin_dao = admin_dao;
        this.views = views;
        //llamamos a la escucha el boton de iniciar sesion
        this.views.btn_enter.addActionListener(this);

        //le damos a la escucha al label username
        this.views.txt_username.addKeyListener(this);
        //le damos a la escucha al label password
        this.views.txt_password.addKeyListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == views.btn_enter) {
            //obtenemos el texto de los campos
            String email = views.txt_username.getText().trim();
            String pass = String.valueOf(views.txt_password.getPassword());
            
            //hacemos la consulta del login para saber si la informacion esta en la base de datos
            //creamos la variable admin que ya la instancionamos y le pasamos la consulta
            admin = admin_dao.LoginQuery(email, pass);
            //validamos que estos campos no esten vacios en la base de datos
            if (admin.getCorreo() != null) {
                if (admin.getRol().equals("Administrador")) {
                    System_section system = new System_section();
                    system.setVisible(true);
                } else {
                    //se puede validar otros roles
                }
                //cerramos la ventana de login 
                this.views.dispose();
            }else{
                //si es null imprimimos que el correo o contraseña son incorrectos
                JOptionPane.showMessageDialog(null, "Correo o contraseña incorrectos");
                
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == views.txt_username || e.getSource() == views.txt_password) {
            String username = views.txt_username.getText();
            String pass = String.valueOf(views.txt_password.getPassword());
            if (!username.isEmpty() && !pass.isEmpty()) {
                views.btn_enter.setEnabled(true);
            } else {
                views.btn_enter.setEnabled(false);
            }
        }
    }

}
