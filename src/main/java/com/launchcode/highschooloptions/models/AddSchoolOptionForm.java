package com.launchcode.highschooloptions.models;

import com.launchcode.highschooloptions.models.School;
import com.launchcode.highschooloptions.models.User;

import javax.validation.constraints.NotNull;

public class AddSchoolOptionForm {
    @NotNull
    private int userId;

    @NotNull
    private int schoolId;

    private User user;

    private Iterable<School> schools;

    public User getUser() {
        return user;
    }

    public AddSchoolOptionForm() {};

    public AddSchoolOptionForm(Iterable<School> schools, User user) {
        this.schools = schools;
        this.user = user;
    };

    public Iterable<School> getSchools() {
        return schools;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }


}
