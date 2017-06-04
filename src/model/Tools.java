/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.ctrl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import view.AddUser;
import view.FlightWindow;

/**
 *
 * @author Eneko
 */
public class Tools {

    static ctrl con;
    
    public static void setCon(ctrl cn){
        con = cn;
    }
    
    public static ResultSet query(String s, boolean b) {
        /* If b is true this is going to be a SELECT and returns the result,
        but if b is false it means we are going to do an INSERT/DELETE
         */

        //Set up the conexion
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");
        Connection conn;
        Statement stmt;
        if (b) {

            ResultSet rs;
            try {
                //Try to connect
                conn = (Connection) dataSource.getConnection();
                stmt = (Statement) conn.createStatement();
                rs = stmt.executeQuery(s);
                //Return the result of the query
                return rs;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "There has been an error with the DB.",
                        "Oh oh",
                        JOptionPane.ERROR_MESSAGE);
            }
        } else {
            try {
                //Try to connect
                conn = (Connection) dataSource.getConnection();
                stmt = (Statement) conn.createStatement();
                stmt.execute(s);

                return null;
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null,
                        "There has been an error with the DB.",
                        "Oh oh",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        return null;
    }

    public static boolean validateUser(AddUser au) {
        String dni = au.dniTB.getText();
        List<String> errors = new ArrayList<String>();

        if (dni.length() == 9 && Character.isLetter(dni.charAt(8))) {
            String num = dni.substring(0, 8);
            try {
                int i = Integer.parseInt(num);

            } catch (Exception ex) {
                errors.add("DNI number must have 8 digits plus one letter");
            }

        } else {
            errors.add("DNI number must have 8 digits plus one letter");
        }

        if (au.nameTB.getText().isEmpty()) {
            errors.add("Name field is empty");
        } else {

            for (int i = 0; i < 10; i++) {
                if (au.nameTB.getText().contains(String.valueOf(i))) {
                    errors.add("Name field can't contain numbers");
                    i = 10;
                }
            }

        }
        if (au.surnameTB.getText().isEmpty()) {
            errors.add("Surname field is empty");
        } else {

            for (int i = 0; i < 10; i++) {
                if (au.surnameTB.getText().contains(String.valueOf(i))) {
                    errors.add("Surname field can't contain numbers");
                    i = 10;
                }
            }

        }
        if (au.birthTB.getText().isEmpty()) {
            errors.add("Birthdate field is empty");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);
                LocalDate birth = LocalDate.parse(au.birthTB.getText(), formatter);
                if (birth.getYear() < LocalDate.now().getYear() - 100) {

                    errors.add("You can't be older than 100 years old");
                } else if (birth.getYear() > LocalDate.now().getYear() - 4) {

                    errors.add("You must be older than 4 years old");
                } else if (birth.isAfter(LocalDate.now())) {
                    errors.add("Birthdate must be a real date");

                }

            } catch (Exception ex) {
                errors.add("Incorrect date format");
            }
        }
        if (au.phoneTB.getText().isEmpty()) {
            errors.add("Phone field is empty");
        } else {
            if (au.phoneTB.getText().length() == 9) {
                try {
                    int phone = Integer.parseInt(au.phoneTB.getText());

                } catch (Exception ex) {
                    errors.add("Phone number can only contain numbers");

                }
            } else {
                errors.add("Phone number can't contain letters");
            }
        }
        if (au.usernameTB.getText().isEmpty()) {
            errors.add("Username field is empty");
        } else if (!au.usernameTB.getText().matches("[A-Za-z0-9_]+")) {

            errors.add("Username contains invalid characters");
        }
        if (au.pwTB.getText().isEmpty()) {
            errors.add("Password field is empty");
        }
        if (au.emailTB.getText().isEmpty()) {
            errors.add("Email field is empty");
        } else {
            String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
            java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
            java.util.regex.Matcher m = p.matcher(au.emailTB.getText());
            if (!m.matches()) {
                errors.add("Invalid email");
            }

        }
        if (errors.size() == 0) {
            au.jTextArea2.setText("");
            return true;
        } else {
            au.jTextArea2.setText("");
            au.jTextArea2.append("List of errors:\n");
            for (int i = 0; i < errors.size(); i++) {
                au.jTextArea2.append(errors.get(i) + "\n");
            }
            return false;
        }

    }

    public static boolean validateFlight(FlightWindow fw) {
        List<String> errors = new ArrayList<>();
        boolean TicIsInt = false;
        if (fw.durTF.getText().isEmpty()) {
            errors.add("Duration field is empty");
        } else {
            if (fw.durTF.getText().length() < 4) {
                try {
                    int parseInt = Integer.parseInt(fw.durTF.getText());
                } catch (Exception x) {
                    errors.add("Duration can only contain numbers");
                }
            } else {
                errors.add("Duration must be less than 1000 minutes");
            }

        }
        if (fw.oriTF.getText().isEmpty()) {
            errors.add("Origin field is empty");
        } else {
            for (int i = 0; i < 10; i++) {
                if (fw.oriTF.getText().contains(String.valueOf(i))) {
                    errors.add("Origin field can't contain numbers");
                }
            }
        }
        if (fw.desTF.getText().isEmpty()) {
            errors.add("Destination field is empty");
        } else {
            for (int i = 0; i < 10; i++) {
                if (fw.desTF.getText().contains(String.valueOf(i))) {
                    errors.add("Destination field can't contain numbers");
                }
            }
        }
        if (fw.p1TF.getText().isEmpty()) {
            errors.add("First pilot field is empty");
        } else {
            for (int i = 0; i < 10; i++) {
                if (fw.p1TF.getText().contains(String.valueOf(i))) {
                    errors.add("First pilot field can't contain numbers");
                }
            }
        }
        if (fw.p2TF.getText().isEmpty()) {
            errors.add("Second pilot field is empty");
        } else {
            for (int i = 0; i < 10; i++) {
                if (fw.p2TF.getText().contains(String.valueOf(i))) {
                    errors.add("Second pilot field can't contain numbers");
                }
            }
        }
        if (fw.ticTF.getText().isEmpty()) {
            errors.add("Tickets field is empty");
        } else {
            try {
                Integer.parseInt(fw.ticTF.getText());
                TicIsInt = true;
            } catch (Exception x) {
                errors.add("Tickets field can only contain numbers");
            }
        }
        if (fw.soldTF.getText().isEmpty()) {
            errors.add("Sold Tickets field is empty");
        } else {
            try {
                int sold = Integer.parseInt(fw.soldTF.getText());
                if (!fw.ticTF.getText().isEmpty() && TicIsInt && Integer.parseInt(fw.ticTF.getText()) < sold) {
                    errors.add("Sold tickets are more than total tickets");
                }
            } catch (Exception x) {
                errors.add("Sold Tickets field can only contain numbers");
            }
        }
        if (fw.dateTF.getText().isEmpty()) {
            errors.add("Date field is empty");
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);
                LocalDate date = LocalDate.parse(fw.dateTF.getText(), formatter);
                if (date.isBefore(LocalDate.now())) {

                    errors.add("Date can't be in the past");
                } else if (date.getYear() > LocalDate.now().getYear() + 4) {

                    errors.add("Date can't be afte 4 years from today");
                }
            } catch (Exception e) {
                errors.add("Incorrect date format");
            }
        }
        if (fw.priceTF.getText().isEmpty()) {
            errors.add("Price field is empty");
        } else {
            try {
                int price = Integer.parseInt(fw.priceTF.getText());
                if (price < 15) {
                    errors.add("Price must be higher than 15");
                }
            } catch (Exception e) {
                errors.add("Price field must be a number");
            }
        }
        if (errors.size() == 0) {
            fw.jTextArea2.setText("");
            return true;
        } else {
            fw.jTextArea2.setText("");
            fw.jTextArea2.append("List of errors:\n");
            for (int i = 0; i < errors.size(); i++) {
                fw.jTextArea2.append(errors.get(i) + "\n");
            }
            return false;
        }
    }

    public static int login(String us, String pw) {
        ResultSet rs = query("SELECT id, passwd FROM USERS WHERE username = '" + us + "'", true);
        int ID = -1;
        try {
            rs.next();     
            String s = rs.getString(2);
            if (pw.equals(s)) {
                
                ID = rs.getInt(1);
                con.setID(ID);
            }else{
                JOptionPane.showMessageDialog(null,
                        "Incorrect username or password",
                        "Auth error",
                        JOptionPane.ERROR_MESSAGE);
            }
            return ID;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                        "Incorrect username or password",
                        "Auth error",
                        JOptionPane.ERROR_MESSAGE);
        }
        return ID;
    }
}
