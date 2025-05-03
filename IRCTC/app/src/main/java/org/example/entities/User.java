package org.example.entities;

import java.util.List;

public class User {

    private String Name ;
    private String Password ;
    private String HashPassword;
    private String userId ;

    private List<Ticket> bookedtickets ;


    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId){
        this.Name = name;
        this.Password = password;
        this.HashPassword = hashedPassword;
        this.bookedtickets = ticketsBooked;
        this.userId = userId;
    }
    public User(){}

    public String getName() {
        return Name;
    }

    public String getPassword(){
        return Password;
    }

    public String getHashedPassword() {
        return HashPassword;
    }

    public List<Ticket> getTicketsBooked() {
        return bookedtickets;
    }

    public void printTickets(){
        for (int i = 0; i<bookedtickets.size(); i++){
            System.out.println(bookedtickets.get(i).getTicketInfo());
        }
    }

    public String getUserId() {
        return userId;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setHashedPassword(String hashedPassword) {
        this.HashPassword = hashedPassword;
    }

    public void setTicketsBooked(List<Ticket> ticketsBooked) {
        this.bookedtickets = ticketsBooked;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

