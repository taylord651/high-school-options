package com.launchcode.highschooloptions.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class School {

    @NotNull
    @Size(min=3, max=50, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull
    @Size(min=3, max=50, message = "Address must be between 3 and 50 characters")
    private String address;

    @NotNull
    @Size(min=10, max=14, message = "Phone number must include area code and be formatted as 123-456-7890")
    private String phone;

    @NotNull
    @Size(message = "Select a type of school")
    private String type;

    @NotNull
    @Size(message = "Select a GPA range")
    private String gpa;

    @NotNull
    @Size(message = "Select the minimum MAP score required for acceptance")
    private String map;

    @NotNull
    @Size(message = "Select a specialty")
    private String specialty;

    @NotNull
    @Size(message = "Select sports offered at school")
    private String sports;

    private int schoolId;
    private static int nextId = 1;

    public School(String name, String address, String phone, String type, String gpa, String map, String specialty, String sports) {
        this();
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.gpa = gpa;
        this.map = map;
        this.specialty = specialty;
        this.sports = sports;
    }

    public School() {
        schoolId = nextId;
        nextId++;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
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
