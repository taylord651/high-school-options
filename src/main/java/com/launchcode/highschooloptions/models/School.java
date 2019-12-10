package com.launchcode.highschooloptions.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class School {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=70, message = "Name must be between 3 and 50 characters")
    private String name;

    @NotNull
    @Size(min=3, max=50, message = "Address must be between 3 and 50 characters")
    private String address;

    @NotNull
    @Size(min=10, max=14, message = "Phone number must include area code and be formatted as 123-456-7890")
    private String phone;

    private SchoolType type;

    private SchoolGpa gpa;

    private SchoolMap map;

    private SchoolSpecialty specialty;

    @NotNull(message = "Select sports offered at school")
    private String sports;

    @NotNull
    @NotNull
    @Size(min=7, message = "Please include school website")
    private String website;

    @ManyToMany(mappedBy = "schools")
    private List<User> users;

    public School(String name, String address, String phone, String sports, String website) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.sports = sports;
        this.website = website;
    }

    public School() {}

    public int getId() {
        return id;
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

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public SchoolType getType() {
        return type;
    }

    public void setType(SchoolType type) {
        this.type = type;
    }

    public SchoolGpa getGpa() {
        return gpa;
    }

    public void setGpa(SchoolGpa gpa) {
        this.gpa = gpa;
    }

    public SchoolMap getMap() {
        return map;
    }

    public void setMap(SchoolMap map) {
        this.map = map;
    }

    public SchoolSpecialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(SchoolSpecialty specialty) {
        this.specialty = specialty;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}