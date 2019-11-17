package com.launchcode.highschooloptions.models;

public enum SchoolType {

    PUBLIC ("Public"),
    PRIVATE_RELIGIOUS ("Private Religious"),
    PRIVATE_INDEPENDENT ("Private Independent");

    private final String name;

    SchoolType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
