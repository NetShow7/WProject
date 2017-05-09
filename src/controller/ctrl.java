/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.User;
import model.management;
import model.management.MiObjectInputStream;
import model.management.MiObjectOutputStream;
import view.adduser;
import view.mainw;
import view.show;

/**
 *
 * @author DM3-1-20
 */
public class ctrl implements ActionListener {

    private mainw mw;
    private adduser au;
    private show sh;
    private management mng = new management();

    public ctrl(mainw mw, adduser au, show sh, management mng) {
        this.mw = mw;
        this.au = au;
        this.sh = sh;
        this.mng = mng;
        //Listeners
        this.mw.jMenuItem1.addActionListener(this);
        this.mw.jMenuItem2.addActionListener(this);
        this.au.jButton1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mw.jMenuItem1) {//Add new user menu item
            au.setVisible(true);
        } else if (e.getSource() == au.jButton1) { //add new user button
            User user = new User(); //Create new user

            //Asign values to the user
            user.setDni(Integer.parseInt(au.dniTB.getText()));
            user.setName(au.nameTB.getText());
            user.setSurname(au.surnameTB.getText());
            user.setBirth(au.birthTB.getText());
            user.setAddress(au.addressTB.getText());
            user.setPhone(Integer.parseInt(au.phoneTB.getText()));
            user.setUsername(au.usernameTB.getText());
            user.setPassword(au.pwTB.getText());
            user.setEmail(au.emailTB.getText());

            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Users.ser", true))) {
                oos.writeObject(user);
                JOptionPane.showMessageDialog(null, "User has been added", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "There has been an error adding the user", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == mw.jMenuItem2) {
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
            dm.setColumnIdentifiers(s);
            sh.jTable1.setModel(dm);
            sh.jTable1.setEnabled(false);

            try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {
                while (true) {//Reads users from file and shows their members
                    User user = (User) ois.readObject();
                    Vector<String> vector = new Vector<String>();
                    vector.add(Integer.toString(user.getDni()));
                    vector.add(user.getName());
                    vector.add(user.getSurname());
                    vector.add(user.getBirth().toString());
                    vector.add(user.getAddress());
                    vector.add(Integer.toString(user.getPhone()));
                    vector.add(user.getUsername());
                    vector.add(user.getPassword());
                    vector.add(user.getEmail());
                    
                    dm.addRow(vector);
                }

            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file (Probably there are no users saved)");
            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {
            }

            sh.setVisible(true);
        }
    }

}
