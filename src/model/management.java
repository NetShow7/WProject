/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import view.search;

/**
 *
 * @author DM3-1-20
 */
public class management {

    public static void writeUser(User us) {
        boolean exist = false;
        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {
            while (!exist) {//Reads users from file and checks if exists
                User user2 = (User) ois.readObject();
                if (us.getDni() == user2.getDni()) {
                    exist = true;
                }

            }

        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(management.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!exist) {

            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Users.ser", true))) {
                oos.writeObject(us);
                JOptionPane.showMessageDialog(null, "User has been added", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "There has been an error adding the user", "Error", JOptionPane.ERROR_MESSAGE);

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "There has been an error adding the user", "Error", JOptionPane.ERROR_MESSAGE);

            }
        } else {
            JOptionPane.showMessageDialog(null, "User already exist", "Warning", JOptionPane.WARNING_MESSAGE);

        }
    }
//Connection

    public static User searchUser(int dni, search se) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");
        Connection conn;
        Statement stmt;
        ResultSet rs;
        try {
            conn = (Connection) dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERS WHERE dni ="+dni);
            User user = new User();
            rs.next();
            
            user.setDni(rs.getString(2));
            user.setName(rs.getString(3));
            user.setSurname(rs.getString(4));
            user.setBirth(rs.getString(5));
            user.setAddress(rs.getString(6));
            user.setPhone(rs.getInt(7));
            user.setUsername(rs.getString(8));
            user.setPassword(rs.getString(9));
            user.setEmail(rs.getString(10));
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(management.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;


        
    }

    public static void deleteUser(String dni) {
        boolean aurkitua = false, exist = true;
        ArrayList<User> users = new ArrayList<>();//Arraylist for saving the users

        try (MiObjectInputStream ois = new MiObjectInputStream(new FileInputStream("C:\\program\\Users.ser"))) {

            while (true) { //Reads users from file and saves them in "users" arraylist
                User usr = (User) ois.readObject();
                if (usr.getDni() != dni) {//If it is not the user to be deleted saves it in the arraylist
                    users.add(usr);
                } else {
                    JOptionPane.showMessageDialog(null, "User has been added", "Success", JOptionPane.INFORMATION_MESSAGE);

                }

            }

        } catch (EOFException ex) {
            if (!aurkitua) {
                System.out.println("There is no user with that DNI");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Can't find file");
            exist = false;
        } catch (IOException ex) {
            System.out.println("You've typed something wrong");
        } catch (ClassNotFoundException ex) {
        }

        if (exist) {//If the file exist
            try (MiObjectOutputStream oos = new MiObjectOutputStream(new FileOutputStream("C:\\program\\Users.ser"))) {
                for (int i = 0; i < users.size(); i++) {//Writes all the users
                    oos.writeObject(users.get(i));
                }
            } catch (FileNotFoundException ex) {
                System.out.println("Can't find file");
            } catch (IOException ex) {
                System.out.println("You've typed something wrong");

            }
        }
    }

    public static class MiObjectInputStream extends ObjectInputStream {

        public MiObjectInputStream(ObjectInputStream out) throws IOException {
            super(out);
        }

        protected MiObjectInputStream() throws IOException, SecurityException {
            super();
        }

        public MiObjectInputStream(InputStream inputStream) throws IOException {
            super(inputStream);
        }

        protected void readStreamHeader() throws IOException {
        }

    }

    public static class MiObjectOutputStream extends ObjectOutputStream {

        /**
         * Constructor que recibe OutputStream
         */
        public MiObjectOutputStream(OutputStream out) throws IOException {

            super(out);

        }

        /**
         * Constructor sin parÃ¡metros
         */
        protected MiObjectOutputStream() throws IOException, SecurityException {

            super();

        }

        /**
         * RedefiniciÃ3n del mÃ©todo de escribir la cabecera para que no haga
         * nada.
         *
         */
        protected void writeStreamHeader() throws IOException {

        }

    }
}
