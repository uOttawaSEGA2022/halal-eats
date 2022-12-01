package com.example.mealerapp;

public class Chef {

    String username;
    String suspended;

    String firstname;
    String lastname;

    String address;
    String  rating;

    public Chef(String username, String suspended, String address, String firstname, String lastname, String rating) {
        this.username = username;
        this.suspended = suspended;
        this.address = address;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSuspended() {
        return suspended;
    }

    public void setSuspended(String suspended) {
        this.suspended = suspended;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }





}
