package com.launchcode.highschooloptions.models;

public enum SchoolGender {

        Coed ("Coed (Boys and Girls)"),
        All_Boys ("All Boys"),
        All_Girls  ("All Girls");

        private final String name;

        SchoolGender(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
