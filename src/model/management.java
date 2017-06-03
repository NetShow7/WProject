/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static model.Tools.login;
import static model.Tools.query;

/**
 *
 * @author DM3-1-20
 */
public class management {

    public static void writeUser(User us) {

        query("INSERT INTO USERS (dni,name,surname,birth,address,phone,username,passwd,email) VALUES ('" + us.getDni() + "','" + us.getName() + "','" + us.getSurname() + "','" + us.getBirth() + "','" + us.getAddress() + "'," + us.getPhone() + ",'" + us.getUsername() + "','" + us.getPassword() + "','" + us.getEmail() + "')", false);
        JOptionPane.showMessageDialog(null,
                "User successfully added.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void writeFlight(Flight fl) {
        query("INSERT INTO FLIGHTS (duration,origin,destination,pilot1,pilot2,tickets,tickets_sold,fdate,price) VALUES ('" + fl.getDuration() + "','" + fl.getOrigin() + "','" + fl.getDestination() + "','" + fl.getPilot1() + "','" + fl.getPilot2() + "'," + fl.getTickets() + ",'" + fl.getTickets_sold() + "','" + fl.getDate() + "','" + fl.getPrice() + "')", false);
        JOptionPane.showMessageDialog(null,
                "User successfully added.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static User searchUser(String dni) {

        ResultSet rs;
        try {

            rs = query("SELECT * FROM USERS WHERE dni = '" + dni + "'", true);
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

    public static Flight searchFlight(String des) {

        ResultSet rs;
        try {

            rs = query("SELECT * FROM FLIGHTS WHERE destination = '" + des + "'", true);
            Flight flight = new Flight();
            rs.next();

            flight.setDuration(rs.getInt(2));
            flight.setOrigin(rs.getString(3));
            flight.setDestination(rs.getString(4));
            flight.setPilot1(rs.getString(5));
            flight.setPilot2(rs.getString(6));
            flight.setTickets(rs.getInt(7));
            flight.setTickets_sold(rs.getInt(8));
            flight.setDate(rs.getString(9));
            flight.setPrice(rs.getInt(10));
            return flight;
        } catch (SQLException ex) {

        }
        return null;
    }

    public static ArrayList<User> getUsers() {

        ArrayList<User> users = new ArrayList<>();
        ResultSet rs;
        try {

            rs = query("SELECT * FROM USERS", true);

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
        ResultSet rs;
        ArrayList<Flight> flights = new ArrayList<>();

        try {

            rs = query("SELECT * FROM FLIGHTS", true);

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

    public static ArrayList<Flight> getRess(String usn, String pw) {

        ArrayList<Flight> flights = new ArrayList<>();

        ResultSet rs;
        try {
            int id = login(usn, pw);
            if (id != -1) {
                rs = query("SELECT * FROM FLIGHTS INNER JOIN RESERVATIONS ON FLIGHTS.id = RESERVATIONS.f_id WHERE u_id = '" + id + "'", true);

                while (rs != null && rs.next()) {
                   
                    Flight flight = new Flight();
                    flight.setFlightid(Integer.parseInt(rs.getString(1)));

                    flight.setOrigin(rs.getString(3));
                    flight.setDestination(rs.getString(4));

                    flight.setDate(rs.getString(9));

                    flights.add(flight);
                }
                if (flights.isEmpty()) {
                    JOptionPane.showMessageDialog(null,
                            "You have no reservations",
                            "Something went wrong",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                return flights;

            } else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect username or password",
                        "Auth error",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "There are no entries in the DB",
                    "Can't show Reservations",
                    JOptionPane.ERROR_MESSAGE);
        }
        return flights;
    }
    public static void delRes(String id, int uid){
                query("DELETE FROM RESERVATIONS WHERE f_id = '" + id + "' AND u_id = '"+uid+"'", false);
                JOptionPane.showMessageDialog(null,
                "The reservation has been successfully deleted from the DB",
                "Reservation deleted",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public static void deleteUser(String dni) {

        query("DELETE FROM USERS WHERE dni = '" + dni + "'", false);
        JOptionPane.showMessageDialog(null,
                "The user has been successfully deleted from the DB",
                "User deleted",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void deleteFlight(int id) {
        query("DELETE FROM FLIGHTS WHERE id = '" + id + "'", false);
        JOptionPane.showMessageDialog(null,
                "The flight has been successfully deleted from the DB",
                "Flight deleted",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public static void reserve(String usern, String passwd, int f_id) {

        ResultSet rs;
        try {

            rs = query("SELECT id,passwd FROM USERS WHERE username='" + usern + "'", true);
            rs.next();
            if (passwd.equals(rs.getString(2))) {
                query("INSERT INTO RESERVATIONS(f_id,u_id) VALUES (" + f_id + "," + rs.getString(1) + ")", false);
                JOptionPane.showMessageDialog(null,
                        "Reservation successfully made.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Incorrect username or password",
                        "Auth error",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Incorrect username or password",
                    "Auth error",
                    JOptionPane.INFORMATION_MESSAGE);
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
