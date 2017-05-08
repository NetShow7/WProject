/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.User;
import model.management;
import model.management.MiObjectOutputStream;
import view.adduser;
import view.mainw;

/**
 *
 * @author DM3-1-20
 */
public class ctrl implements ActionListener {

    private mainw mw;
    private adduser au;
    private management mng = new management();

    public ctrl(mainw mw, adduser au, management mng) {
        this.mw = mw;
        this.au = au;
        this.mng = mng;
        //Listeners
        this.mw.jMenuItem1.addActionListener(this);
        this.au.jButton1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mw.jMenuItem1) {
            au.setVisible(true);
        } else if (e.getSource() == au.jButton1) { //add new user
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
        }
    }

}
