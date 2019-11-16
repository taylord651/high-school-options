package com.launchcode.highschooloptions.models;

public class School {
    private String name;
    private String address;
    private String phone;
    private String type;
    private String gpa;
    private String map;
    private String specialty;
    private String sports;

    public School(String name, String address, String phone, String type, String gpa, String map, String specialty, String sports) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.gpa = gpa;
        this.map = map;
        this.specialty = specialty;
        this.sports = sports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGpa() {
        return gpa;
    }

    public void setGpa(String gpa) {
        this.gpa = gpa;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }
}
