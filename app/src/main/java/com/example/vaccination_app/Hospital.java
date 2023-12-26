package com.example.vaccination_app;

public class Hospital {

    private String hospitalname, hospitaldesc;
    int image;

    public Hospital(String hospitalname, String hospitaldesc, int image) {
        this.hospitalname = hospitalname;
        this.hospitaldesc = hospitaldesc;
        this.image = image;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public String getHospitaldesc() {
        return hospitaldesc;
    }

    public int getImage() {
        return image;
    }
}
