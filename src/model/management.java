/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DM3-1-20
 */
public class management {
    
    public static void writeUser(User us){
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
                } catch (FileNotFoundException ex) {
                    System.out.println("Can't find file");
                } catch (IOException ex) {
                    Logger.getLogger(management.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println("");
                System.out.println("/!\\ User already exists.");
                System.out.println("");
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


