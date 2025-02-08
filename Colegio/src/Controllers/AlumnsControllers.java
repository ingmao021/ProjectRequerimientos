package Controllers;

import static Models.AdminDao.admin_rol;
import Models.Alumns;
import Models.AlumnsDao;

import Views.System_section;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class AlumnsControllers implements ActionListener, KeyListener, MouseListener {

    private Alumns alumn;
    private AlumnsDao alumn_dao;
    private System_section view;

    DefaultTableModel model = new DefaultTableModel();
    String rol = admin_rol;

    public AlumnsControllers(Alumns alumn, AlumnsDao alumn_dao, System_section view) {
        this.alumn = alumn;
        this.alumn_dao = alumn_dao;
        this.view = view;

        this.view.btn_register_alumno.addActionListener(this);
        this.view.btn_update_alumno.addActionListener(this);
        this.view.btn_delete_alumno.addActionListener(this);
        this.view.btn_cancel_alumn.addActionListener(this);

        //llamamos a la escucha el label de buscar alumno
        this.view.txt_search_alumn.addKeyListener(this);
        //llamamos a la escucha el label del correo
        this.view.txt_alumn_email.addKeyListener(this);

        //llamamos a la escucha la tabla
        this.view.table_alumns.addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == view.btn_register_alumno) {
            if (!validefieldsEmpty()) {
                insertAlumns();

                if (alumn_dao.registerAlumnQuery(alumn)) {
                    cleanFields();
                    JOptionPane.showMessageDialog(null, "Registro con exito");
                    cleanTableModel();
                    listAlumns();
                    view.txt_alumn_password.setEditable(true);
                } else {

                }
            } else {
                JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
            }

        } else if (e.getSource() == view.btn_update_alumno) {

            if (view.table_alumns.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(null, "Selecciona un alumno de la tabla");
            } else {
                if (!validefieldsEmpty()) {
                    insertAlumns();

                    if (alumn_dao.updateAlumnQuery(alumn)) {
                        JOptionPane.showMessageDialog(null, "Datos del alumno modificados con exito");
                        cleanFields();
                        cleanTableModel();
                        listAlumns();
                        enableFields();
                        //activamos el boton de registrar
                        view.btn_register_alumno.setEnabled(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ocurrio un error al modificar los datos del alumno");

                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Todos los campos son obligatorios");
                }
            }
        } else if (e.getSource() == view.btn_delete_alumno) {
            int row = view.table_alumns.getSelectedRow();
            //validamos que si la fila es = a -1 es que no se ha seleccionado
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar un alumno de la tabla");
            } else {
                int id_alumn = Integer.parseInt(view.table_alumns.getValueAt(row, 0).toString());

                int Question_delete = JOptionPane.showConfirmDialog(null, "¿Estas seguro que quieres eliminar este alumno?");

                if (Question_delete == 0 && alumn_dao.deleteAlumnQuery(id_alumn) != false) {
                    JOptionPane.showMessageDialog(null, "Alumno eliminado con exito");
                    cleanFields();
                    cleanTableModel();
                    listAlumns();
                    enableFields();

                    //habilitar boton de registrar
                    view.btn_register_alumno.setEnabled(true);
                } else {
                    //eliminamos los campos y habilitamos el boton de registrar
                    cleanFields();
                    //habilitamos los campos
                    enableFields();
                    view.btn_register_alumno.setEnabled(true);

                    //limpiamos la seleccion de la tabla
                    view.table_alumns.clearSelection();
                }
            }
        } else if (e.getSource() == view.btn_cancel_alumn) {
            cleanFields();
            view.btn_register_alumno.setEnabled(true);
            view.table_alumns.clearSelection();

            //habilitamos los campos
            enableFields();

        }
    }

    public void insertAlumns() {
        alumn.setId(Integer.parseInt(view.txt_code_alumn.getText()));
        alumn.setName(view.txt_alumn_name.getText());
        alumn.setOneLastName(view.txt_alumn_lastnameOne.getText());
        alumn.setsecondLastName(view.txt_alumn_lastnameSecond.getText());
        alumn.setAddress(view.txt_alumn_address.getText());
        alumn.setEmail(view.txt_alumn_email.getText());
        alumn.setPassword(String.valueOf(view.txt_alumn_password.getPassword()));
        alumn.setTelephone(view.txt_alumn_telephone.getText());
        alumn.setRol(view.cmb_rol.getSelectedItem().toString());
    }

    public boolean validefieldsEmpty() {
        return view.txt_alumn_address.getText().equals("")
                || view.txt_code_alumn.getText().equals("")
                || view.txt_alumn_telephone.getText().equals("")
                || view.txt_alumn_lastnameOne.getText().equals("")
                || view.txt_alumn_lastnameSecond.getText().equals("")
                || view.txt_alumn_lastnameOne.getText().equals("")
                || view.txt_alumn_password.getPassword().equals("")
                || view.txt_alumn_email.getText().equals("")
                || view.txt_alumn_name.getText().equals("");

    }

    public void cleanFields() {
        view.txt_code_alumn.setText("");
        view.txt_alumn_name.setText("");
        view.txt_alumn_lastnameOne.setText("");
        view.txt_alumn_lastnameSecond.setText("");
        view.txt_alumn_address.setText("");
        view.txt_alumn_telephone.setText("");
        view.txt_alumn_password.setText("");
        view.txt_alumn_email.setText("");

    }

    public void listAlumns() {
        if (rol.equals("Administrador")) {
            String search = view.txt_search_alumn.getText();
            List<Alumns> list = alumn_dao.listAlumnsQuery(search);

            model = (DefaultTableModel) view.table_alumns.getModel();
            Object[] obj = new Object[7];

            for (int i = 0; i < list.size(); i++) {
                obj[0] = list.get(i).getId();
                obj[1] = list.get(i).getName();
                obj[2] = list.get(i).getOneLastName();
                obj[3] = list.get(i).getsecondLastName();
                obj[4] = list.get(i).getAddress();
                obj[5] = list.get(i).getEmail();
                obj[6] = list.get(i).getTelephone();

                model.addRow(obj);
            }
            view.table_alumns.setModel(model);

        }
    }

    public void cleanTableModel() {
        for (int i = 0; i < model.getRowCount(); i++) {
            model.removeRow(i);
            i--;
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
        //si interactua con el label realizamos la busqueda
        if (e.getSource() == view.txt_search_alumn) {
            cleanTableModel();
            listAlumns();
        }

        //aqui para que al escribir el correo me coloque la identificacion en la contraseña
        //ya que esta va hacer la contraseña del alumno
        if (e.getSource() == view.txt_alumn_email) {
            view.txt_alumn_password.setText(view.txt_code_alumn.getText());
            view.txt_alumn_password.setEditable(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == view.table_alumns) {
            int row = view.table_alumns.rowAtPoint(e.getPoint());

            view.txt_code_alumn.setText(view.table_alumns.getValueAt(row, 0).toString());
            view.txt_alumn_name.setText(view.table_alumns.getValueAt(row, 1).toString());
            view.txt_alumn_lastnameOne.setText(view.table_alumns.getValueAt(row, 2).toString());
            view.txt_alumn_lastnameSecond.setText(view.table_alumns.getValueAt(row, 3).toString());
            view.txt_alumn_address.setText(view.table_alumns.getValueAt(row, 4).toString());
            view.txt_alumn_email.setText(view.table_alumns.getValueAt(row, 5).toString());
            view.txt_alumn_telephone.setText(view.table_alumns.getValueAt(row, 6).toString());
            view.txt_alumn_password.setText(view.txt_code_alumn.getText());

            //deshabilitamos el de registrar
            view.btn_register_alumno.setEnabled(false);

            //deshabilitamos los campos como identificacion, nombres, primer apellido
            //segundo apellido, la contraseña(es la identificacion). ya que si se va hacer un cambio no pueden ser estos
            view.txt_code_alumn.setEditable(false);
            view.txt_alumn_name.setEditable(false);
            view.txt_alumn_lastnameOne.setEditable(false);
            view.txt_alumn_lastnameSecond.setEditable(false);
            view.txt_alumn_password.setEditable(false);

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public void enableFields() {
        view.txt_alumn_name.setEditable(true);
        view.txt_code_alumn.setEditable(true);
        view.txt_alumn_lastnameOne.setEditable(true);
        view.txt_alumn_lastnameOne.setEditable(true);
        view.txt_alumn_lastnameSecond.setEditable(true);
        view.txt_alumn_password.setEditable(true);
    }

}
