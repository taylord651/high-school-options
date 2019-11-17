package com.launchcode.highschooloptions.models;

public enum SchoolGpa {

        HIGH_GPA ("3.5 - 4.0"),
        MEDIUM_GPA ("3.0 - 3.5"),
        LOW_GPA  ("2.5 - 3.0"),
        NO_MINIMUM_GPA ("Lower than 2.5");

        private final String name;

        SchoolGpa(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
