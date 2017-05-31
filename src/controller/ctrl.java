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
import model.Flight;
import model.User;
import model.management;
import static model.management.deleteUser;
import static model.management.getFlights;
import static model.management.getUsers;
import static model.management.reserve;
import static model.management.searchUser;
import static model.management.validateUser;
import static model.management.writeUser;
import view.adduser;
import view.DeleteUser;
import view.mainw;
import view.SearchUser;
import view.ShowUsers;
import view.addres;

/**
 *
 * @author DM3-1-20
 */
public class ctrl implements ActionListener {

    private mainw mw;
    private adduser add_u;
    private ShowUsers show_u;
    private SearchUser search_u;
    private DeleteUser del_u;
    private addres ar;
    private management mng = new management();

    public ctrl(mainw mw, adduser au, ShowUsers sh, SearchUser se, DeleteUser del, management mng, addres ar) {
        this.mw = mw;
        add_u = au;
        show_u = sh;
        search_u = se;
        del_u = del;
        this.mng = mng;
        this.ar = ar;
        //Listeners
        this.mw.adduser.addActionListener(this);
        this.mw.showusers.addActionListener(this);
        this.mw.deleteuser.addActionListener(this);
        this.mw.searchuser.addActionListener(this);
        this.mw.newres.addActionListener(this);
        this.del_u.jButton1.addActionListener(this);//Delete button
        this.search_u.jButton1.addActionListener(this);//Search button
        this.add_u.jButton1.addActionListener(this);//Add button
        this.ar.jButton1.addActionListener(this);//Make reservation button
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mw.adduser) {//Add new user menu item
            add_u.setVisible(true);
        } else if (e.getSource() == mw.showusers) {//Show all the users
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
            dm.setColumnIdentifiers(s);
            show_u.jTable1.setModel(dm);
            show_u.jTable1.setEnabled(false);
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

            show_u.setVisible(true);
        } else if (e.getSource() == mw.deleteuser) {//Delete user
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
            dm.setColumnIdentifiers(s);

            del_u.jTable1.setModel(dm);

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

            del_u.setVisible(true);
        } else if (e.getSource() == mw.searchuser) {//Search user
            search_u.setVisible(true);

        }else if (e.getSource() == mw.newres) {
            DefaultTableModel dm = new DefaultTableModel(0, 0);
            String s[] = new String[]{"ID", "Duration (min)", "Origin", "Destination", "First pilot", "Second pilot", "Available seats", "Date", "Price (€)"};
            dm.setColumnIdentifiers(s);
            ar.jTable1.setModel(dm);
            ar.jTable1.setEnabled(true);
            List<Flight> flights = getFlights();
            for (int i = 0; i < flights.size(); i++) {

                Vector<String> vector = new Vector<>();

                vector.add(Integer.toString(flights.get(i).getFlightid()));
                vector.add(Integer.toString(flights.get(i).getDuration()));
                vector.add(flights.get(i).getOrigin());
                vector.add(flights.get(i).getDestination());
                vector.add(flights.get(i).getPilot1());
                vector.add(flights.get(i).getPilot2());
                vector.add(Integer.toString(flights.get(i).getTickets() - flights.get(i).getTickets_sold()));
                vector.add(flights.get(i).getDate().toString());
                vector.add(Float.toString(flights.get(i).getPrice()));

                dm.addRow(vector);
            }
            ar.setVisible(true);
        } 
        
        else if (e.getSource() == add_u.jButton1) { //add new user button

            if (validateUser(add_u)) {

                User user = new User(); //Create new user
                //Asign values to the user
                user.setDni(add_u.dniTB.getText());
                user.setName(add_u.nameTB.getText());
                user.setSurname(add_u.surnameTB.getText());
                user.setBirth(add_u.birthTB.getText());
                user.setAddress(add_u.addressTB.getText());
                user.setPhone(Integer.parseInt(add_u.phoneTB.getText()));
                user.setUsername(add_u.usernameTB.getText());
                user.setPassword(add_u.pwTB.getText());
                user.setEmail(add_u.emailTB.getText());
                
                writeUser(user);
            }
        } else if (e.getSource() == search_u.jButton1) {//Search for a user button
            search_u.jLabel2.setVisible(false);
            String dni = search_u.jTextField1.getText();
            User user = searchUser(dni);
            if (user != null) {//Create table and show the user
                DefaultTableModel dm = new DefaultTableModel(0, 0);
                String s[] = new String[]{"DNI", "Name", "Surname", "Birth", "Address", "Phone", "Username", "Password", "Email"};
                dm.setColumnIdentifiers(s);
                search_u.jTable1.setModel(dm);
                search_u.jTable1.setEnabled(false);
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
                search_u.jLabel2.setText("Can't find user");
                search_u.jLabel2.setVisible(true);
            }

        } 
        
        
        
        else if (e.getSource() == del_u.jButton1) {
            if (del_u.jTable1.getSelectionModel().isSelectionEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "You must select one row",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                int i = del_u.jTable1.getSelectedRow();
                String id = (String) del_u.jTable1.getValueAt(i, 0);
                deleteUser(id);
                int selectedRow = del_u.jTable1.getSelectedRow();
                DefaultTableModel dm = (DefaultTableModel) del_u.jTable1.getModel();
                dm.removeRow(selectedRow);
                del_u.jTable1.setModel(dm);
            }
        } else if (e.getSource() == ar.jButton1) {//Reservation button
            

            String un = ar.jTextField1.getText();
            String pw = new String(ar.jPasswordField1.getPassword());
            String id =  (String) ar.jTable1.getValueAt(ar.jTable1.getSelectedRow(), 0);
            int f_id = Integer.parseInt(id);
            reserve(un,pw,f_id);
        }

    }
}
