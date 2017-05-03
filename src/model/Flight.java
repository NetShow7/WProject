package model;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Flight implements Serializable {

    //Members
    private int flightid;
    private int duration;
    private String origin;
    private String destination;
    private String pilot1;
    private String pilot2;
    private int tickets;
    private int tickets_sold;
    private LocalDate date;
    private float price;

    //Constructors
    public Flight() {

    }

    public Flight(int id, int dur, String from, String to, String p1, String p2, int tick, int t_sold, LocalDate date, float price) {
        flightid = id;
        duration = dur;
        this.origin = from;
        this.destination = to;
        pilot1 = p1;
        pilot2 = p2;
        tickets = tick;
        tickets_sold = t_sold;
        this.date = date;
        this.price = price;
    }

    Flight(String empty) {
        
    }

    //Getters
    public int getFlightid() {
        return flightid;
    }

    public int getDuration() {
        return duration;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getPilot1() {
        return pilot1;
    }

    public String getPilot2() {
        return pilot2;
    }

    public int getTickets() {
        return tickets;
    }

    public int getTickets_sold() {
        return tickets_sold;
    }

    public LocalDate getDate() {
        return date;
    }

    public float getPrice() {
        return price;
    }

    //Setters
    public void setFlightid(int NewFlightid) {
        flightid = NewFlightid;
    }


    public void setDuration(int NewDuration) {
        duration = NewDuration;
    }



    public void setOrigin(String NewOrigin) {
        origin = NewOrigin;
    }

 

    public void setDestination(String NewDestination) {
        destination = NewDestination;
    }


    public void setPilot1(String NewPilot1) {
        pilot1 = NewPilot1;
    }



    public void setPilot2(String NewPilot2) {
        pilot2 = NewPilot2;
    }



    public void setTickets(int NewTickets) {
        tickets = NewTickets;
    }


    public void setTickets_sold(int NewTickets_sold) {
        tickets_sold = NewTickets_sold;
    }



    public void setDate(LocalDate NewDate) {
        date = NewDate;
    }

    
    public void setDate(String dt) {
        String s = dt.replace("-", " ");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y M d", Locale.ENGLISH);

        date = LocalDate.parse(s, formatter);
    }
    public void setPrice(float NewPrice) {
        price = NewPrice;
    }

    
}
