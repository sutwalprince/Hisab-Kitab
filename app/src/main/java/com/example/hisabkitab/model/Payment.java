package com.example.hisabkitab.model;

public class Payment {
    private int id;
    private String item;
    private String cost;
    private String color;






    public Payment(int id, String item, String cost, String color) {
        this.id = id;
        this.item = item;
        this.cost = cost;
        this.color = color;
    }public Payment(String item, String cost, String color) {
        this.item = item;
        this.cost = cost;
        this.color = color;
    }

    public Payment() {
    }

    public void setColor(String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    public int getId() {
        return id;
    }

    public String getItem() {
        return item;
    }

    public String getCost() {
        return cost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }
}
