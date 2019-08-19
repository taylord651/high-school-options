package com.launchcode.highschooloptions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

/**
 * Created by Domonique Taylor
 */

@Controller
public class HighSchoolOptionsContoller {

    static ArrayList<String> schools = new ArrayList<>();


    // Request path: /options
    @RequestMapping (value = "")
    public String index(Model model) {

        schools.add("Metro");
        schools.add("Collegiate");
        schools.add("McKinley");

        model.addAttribute("schools", schools);
        model.addAttribute("title", "High School Options");

        return "index";
    }

    @RequestMapping(value = "survey", method = RequestMethod.GET)
    public String displaySchoolSurveyForm(Model model) {

        model.addAttribute("title", "School Options Survey");
        return "survey";
    }

    @RequestMapping(value = "survey", method = RequestMethod.POST)
    public String processSchoolSurveyForm(@RequestParam String schoolType, String publicSchool,
                                          String privateReligious, String privateCatholic,
                                          String privateIndependent, String football, String basketball,
                                          String soccer, String tennis, String notes) {

        schools.add(schoolType);

        return "redirect:";


    }
}
