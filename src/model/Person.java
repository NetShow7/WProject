package model;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class Person implements Serializable {

    private int dni;
    private String name;
    private String surname;
    private LocalDate birth;
    private String address;
    private int phone;

    public Person() {
        
    }

    public Person(String s) {

    }

    public int getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone() {
        return phone;
    }

    public void setDni(int a) {
        dni = a;
    }


    public void setName(String NewName) {
        name = NewName;
    }


    public void setSurname(String NewSurname) {
        surname = NewSurname;
    }


    public void setBirth(LocalDate NewBirth) {
        birth = NewBirth;
    }



    public void setBirth(String brth) { //This is used for setting the birth from a file
        String s = brth.replace("-", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        birth = LocalDate.parse(s, formatter);

    }

    public void setAddress(String NewAddress) {
        address = NewAddress;
    }


    public void setPhone(int NewPhone) {
        phone = NewPhone;
    }

}
