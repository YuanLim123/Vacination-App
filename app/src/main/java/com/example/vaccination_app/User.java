package com.example.vaccination_app;

public class User {
    int id;
    String ic, phone, vaccine, name;

    public User(int id, String name, String ic, String phone, String vaccine) {
        this.id = id;
        this.ic = ic;
        this.phone = phone;
        this.vaccine = vaccine;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getIc() {
        return ic;
    }

    public String getPhone() {
        return phone;
    }

    public String getVaccine() {
        return vaccine;
    }

    public String getName() {
        return name;
    }
}
