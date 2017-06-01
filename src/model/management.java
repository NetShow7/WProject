/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.JOptionPane;
import view.AddUser;

/**
 *
 * @author DM3-1-20
 */
public class management {

    public static void writeUser(User us) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
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
            //rs = stmt.executeQuery("INSERT INTO USERS (dni,name,surname,birth,address,phone,username,passwd,email) VALUES ('"+us.getDni()+"','"+us.getName()+"','"+us.getSurname()+"','"+us.getBirth()+"','"+us.getAddress()+"',"+us.getPhone()+",'"+us.getUsername()+"','"+us.getPassword()+"','"+us.getEmail()+"')");
            stmt.execute("INSERT INTO USERS (dni,name,surname,birth,address,phone,username,passwd,email) VALUES ('" + us.getDni() + "','" + us.getName() + "','" + us.getSurname() + "','" + us.getBirth() + "','" + us.getAddress() + "'," + us.getPhone() + ",'" + us.getUsername() + "','" + us.getPassword() + "','" + us.getEmail() + "')");
            JOptionPane.showMessageDialog(null,
                    "User successfully added.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't add user",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    public static User searchUser(String dni) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
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
            rs = stmt.executeQuery("SELECT * FROM USERS WHERE dni = '" + dni + "'");
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

        }
        return null;

    }

    public static ArrayList<User> getUsers() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");

        ArrayList<User> users = new ArrayList<>();

        Connection conn;
        Statement stmt;
        ResultSet rs;
        try {
            conn = (Connection) dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM USERS");

            while (rs.next()) {
                User user = new User();
                user.setDni(rs.getString(2));
                user.setName(rs.getString(3));
                user.setSurname(rs.getString(4));
                user.setBirth(rs.getString(5));
                user.setAddress(rs.getString(6));
                user.setPhone(rs.getInt(7));
                user.setUsername(rs.getString(8));
                user.setPassword(rs.getString(9));
                user.setEmail(rs.getString(10));
                users.add(user);
            }
            return users;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't show users",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;

    }

    public static ArrayList<Flight> getFlights() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");

        ArrayList<Flight> flights = new ArrayList<>();

        Connection conn;
        Statement stmt;
        ResultSet rs;
        try {
            conn = (Connection) dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM FLIGHTS");

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightid(Integer.parseInt(rs.getString(1)));
                flight.setDuration(Integer.parseInt(rs.getString(2)));
                flight.setOrigin(rs.getString(3));
                flight.setDestination(rs.getString(4));
                flight.setPilot1(rs.getString(5));
                flight.setPilot2(rs.getString(6));
                flight.setTickets(Integer.parseInt(rs.getString(7)));
                flight.setTickets_sold(Integer.parseInt(rs.getString(8)));
                flight.setDate(rs.getString(9));
                flight.setPrice(Float.parseFloat(rs.getString(10)));
                flights.add(flight);
            }
            return flights;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't show flights",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static ArrayList<Flight> getRess() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");

        ArrayList<Flight> flights = new ArrayList<>();

        Connection conn;
        Statement stmt;
        ResultSet rs;
        try {
            conn = (Connection) dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM FLIGHTS INNER JOIN RESERVATION ON FLIGHTS.id = RESERVATION.f_id WHERE u_id=");

            while (rs.next()) {
                Flight flight = new Flight();
                flight.setFlightid(Integer.parseInt(rs.getString(1)));

                flight.setOrigin(rs.getString(3));
                flight.setDestination(rs.getString(4));

                flight.setDate(rs.getString(9));

                flights.add(flight);
            }
            return flights;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't show flights",
                    JOptionPane.ERROR_MESSAGE);
        }
        return null;
    }

    public static void deleteUser(String dni) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("skydancer");
        dataSource.setServerName("127.0.0.1");

        Connection conn;
        Statement stmt;

        try {
            conn = (Connection) dataSource.getConnection();
            stmt = (Statement) conn.createStatement();
            stmt.execute("DELETE FROM USERS WHERE dni = '" + dni + "'");
            JOptionPane.showMessageDialog(null,
                    "The user has been successfully deleted from the DB",
                    "User deleted",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't delete user",
                    JOptionPane.ERROR_MESSAGE);
        }
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

    public static void reserve(String usern, String passwd, int f_id) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setPort(3306);
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
            rs = stmt.executeQuery("SELECT id,passwd FROM USERS WHERE username='" + usern + "'");
            rs.next();
            if (passwd.equals(rs.getString(2))) {
                stmt.execute("INSERT INTO RESERVATIONS(f_id,u_id) VALUES (" + f_id + "," + rs.getString(1) + ")");
                JOptionPane.showMessageDialog(null,
                        "Reservation successfully made.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There has been an error with the DB.",
                    "Can't make a reserve.",
                    JOptionPane.ERROR_MESSAGE);
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
