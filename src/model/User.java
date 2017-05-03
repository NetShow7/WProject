package model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;

public class User extends Person implements Serializable {

    private String username;
    private String password;
    private String email;

    public User() {
        super();

    }

    public User(String s) {
        super(s);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String NewUsername) {
        username = NewUsername;
    }

   

    public void setPassword(String NewPassword) {
        password = NewPassword;
    }

    
    public void setEmail(String NewEmail) {
        email = NewEmail;
    }

    
}
