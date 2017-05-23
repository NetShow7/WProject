/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.User;
import model.management;
import static model.management.deleteUser;
import static model.management.getUsers;
import static model.management.searchUser;
import static model.management.validateUser;
import static model.management.writeUser;
import view.adduser;
import view.delete;
import view.mainw;
import view.search;
import view.show;

/**
 *
 * @author DM3-1-20
 */
public class ctrl implements ActionListener {

    private mainw mw;
    private adduser au;
    private show sh;
    private search se;
    private delete del;
    private management mng = new management();

    public ctrl(mainw mw, adduser au, show sh, search se, delete del, management mng) {
        this.mw = mw;
        this.au = au;
        this.sh = sh;
        this.se = se;
        this.del = del;
        this.mng = mng;
        //Listeners
        this.mw.jMenuItem1.addActionListener(this);
        this.mw.jMenuItem2.addActionListener(this);
        this.mw.jMenuItem3.addActionListener(this);
        this.mw.jMenuItem4.addActionListener(this);
        this.del.jButton1.addActionListener(this);
        this.se.jButton1.addActionListener(this);
        this.au.jButton1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mw.jMenuItem1) {//Add new user menu item
            au.setVisible(true);
        } else if (e.getSource() == mw.jMenuItem4) {
            se.setVisible(true);

        } else if (e.getSource() == mw.jMenuItem3) {//Delete user
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
            dm.setColumnIdentifiers(s);

            del.jTable1.setModel(dm);

            List<User> users = getUsers();

            for (int i = 0; i < users.size(); i++) {

                Vector<String> vector = new Vector<>();
                vector.add(users.get(i).getDni());
                vector.add(users.get(i).getName());
                vector.add(users.get(i).getSurname());
                vector.add(users.get(i).getBirth().toString());
                vector.add(users.get(i).getAddress());
                vector.add(Integer.toString(users.get(i).getPhone()));
                vector.add(users.get(i).getUsername());
                vector.add(users.get(i).getPassword());
                vector.add(users.get(i).getEmail());

                dm.addRow(vector);
            }
            //At this point we have the table filled

            del.setVisible(true);
        } else if (e.getSource() == au.jButton1) { //add new user button

            if (validateUser(au)) {

                User user = new User(); //Create new user
                //Asign values to the user
                user.setDni(au.dniTB.getText());
                user.setName(au.nameTB.getText());
                user.setSurname(au.surnameTB.getText());
                user.setBirth(au.birthTB.getText());
                user.setAddress(au.addressTB.getText());
                user.setPhone(Integer.parseInt(au.phoneTB.getText()));
                user.setUsername(au.usernameTB.getText());
                user.setPassword(au.pwTB.getText());
                user.setEmail(au.emailTB.getText());
                JOptionPane.showMessageDialog(null,
                        "There has been an error with the DB.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                writeUser(user);
            }
        } else if (e.getSource() == mw.jMenuItem2) {//Show all the users
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
            dm.setColumnIdentifiers(s);
            sh.jTable1.setModel(dm);
            sh.jTable1.setEnabled(false);
            List<User> users = getUsers();

            for (int i = 0; i < users.size(); i++) {

                Vector<String> vector = new Vector<>();
                vector.add(users.get(i).getDni());
                vector.add(users.get(i).getName());
                vector.add(users.get(i).getSurname());
                vector.add(users.get(i).getBirth().toString());
                vector.add(users.get(i).getAddress());
                vector.add(Integer.toString(users.get(i).getPhone()));
                vector.add(users.get(i).getUsername());
                vector.add(users.get(i).getPassword());
                vector.add(users.get(i).getEmail());

                dm.addRow(vector);
            }

            sh.setVisible(true);
        } else if (e.getSource() == se.jButton1) {//Search for a user
            se.jLabel2.setVisible(false);
            String dni = se.jTextField1.getText();
            User user = searchUser(dni);
            if (user != null) {//Create table and show the user
                DefaultTableModel dm = new DefaultTableModel(0, 0);
                String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
                dm.setColumnIdentifiers(s);
                se.jTable1.setModel(dm);
                se.jTable1.setEnabled(false);
                Vector<String> vector = new Vector<String>();
                vector.add(user.getDni());
                vector.add(user.getName());
                vector.add(user.getSurname());
                vector.add(user.getBirth().toString());
                vector.add(user.getAddress());
                vector.add(Integer.toString(user.getPhone()));
                vector.add(user.getUsername());
                vector.add(user.getPassword());
                vector.add(user.getEmail());

                dm.addRow(vector);
            } else {
                se.jLabel2.setText("Can't find user");
                se.jLabel2.setVisible(true);
            }

        } else if (e.getSource() == del.jButton1) {
            if (del.jTable1.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "You must select one row",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int i = del.jTable1.getSelectedRow();
                String id = (String) del.jTable1.getValueAt(i, 0);
                deleteUser(id);
                int selectedRow = del.jTable1.getSelectedRow();
                DefaultTableModel dm = (DefaultTableModel) del.jTable1.getModel();
                dm.removeRow(selectedRow);
                del.jTable1.setModel(dm);
            }
        }
    }
}
