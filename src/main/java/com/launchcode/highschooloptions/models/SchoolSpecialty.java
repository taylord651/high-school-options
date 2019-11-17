package com.launchcode.highschooloptions.models;

public enum SchoolSpecialty {

    ARTS ("Arts"),
    STEM ("Science, Technology, Engineering, and Math (STEM)"),
    ROTC ("Reserve Officers' Training Corps (ROTC)"),
    GENERAL ("General");

    private final String name;

    SchoolSpecialty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
