package com.launchcode.highschooloptions.models;

public enum SchoolMap {

        ADVANCED ("Advanced"),
        PROFICIENT ("Proficient"),
        BASIC  ("Basic"),
        BELOW_BASIC ("Below Basic");

        private final String name;

        SchoolMap(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

    }
