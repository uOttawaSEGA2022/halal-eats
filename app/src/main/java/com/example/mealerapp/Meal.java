package com.example.mealerapp;

public class Meal {
    String username;

    String mealName;
    String mealType;
    String cuisine;

    String price;
    String offered;
    String desc;


    public Meal(String username, String mealName, String mealType,
                String cuisine, String price, String offered, String desc) {
        this.username = username;
        this.mealName = mealName;
        this.mealType = mealType;
        this.cuisine = cuisine;
        this.price = price;
        this.offered = offered;
        this.desc = desc;


    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffered() {
        return offered;
    }

    public void setOffered(String offered) {
        this.offered = offered;
    }














}