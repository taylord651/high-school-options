package com.launchcode.highschooloptions.models;

import java.util.ArrayList;

/**
 * Created by Domonique Taylor
 */
public class SchoolData {

    static ArrayList<School> schools = new ArrayList<>();

    // getAll
    public static ArrayList<School> getAll() {
        return schools;
    }


    // add
    public static void add(School newSchool) {
        schools.add(newSchool);
    }

    // remove
    public static void remove(int id) {
        School schoolToRemove = getById(id);
        schools.remove(schoolToRemove);
    }

    // getById
    public static School getById(int id) {
        School theSchool = null;

        for (School candidateSchool : schools) {
            if (candidateSchool.getSchoolId() == id) {
                theSchool = candidateSchool;
            }
        }
        return theSchool;
    }
}
