package com.launchcode.highschooloptions.models;

import com.launchcode.highschooloptions.models.School;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=3, max=30, message = "Username required and must be between 3 and 30 characters")
    private String name;

    @NotNull
    @Size(min=3, max=30, message = "Password required and must be between 3 and 30 characters")
    private String password;

    @ManyToMany
    private List<School> schools;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public User() {}

    public void addItem(School item) { schools.add(item); }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<School> getSchools() { return schools; }
}
