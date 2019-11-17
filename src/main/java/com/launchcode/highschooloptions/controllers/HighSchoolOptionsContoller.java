package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.models.School;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Domonique Taylor
 */

@Controller
public class HighSchoolOptionsContoller {

    static ArrayList<School> schools = new ArrayList<>();


    // Request path: / (index template (homepage))
    @RequestMapping (value = "")
    public String index(Model model) {

        model.addAttribute("schools", schools);
        model.addAttribute("title", "High School Options");

        return "index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {

        model.addAttribute("title", "Add School");
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@RequestParam String schoolName, @RequestParam String schoolAddress,
                                 @RequestParam String schoolPhone, @RequestParam String schoolType,
                                 @RequestParam String schoolGpa, @RequestParam String schoolMap,
                                 @RequestParam String schoolSpecialty, @RequestParam String schoolSports) {

        // Stores data entered in add form into ArrayList
        School newSchool = new School(schoolName, schoolAddress, schoolPhone, schoolType, schoolGpa,
                schoolMap, schoolSpecialty, schoolSports);
        schools.add(newSchool);

        // Redirect to /
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSchoolForm(Model model) {
        model.addAttribute("schools", schools);
        model.addAttribute("title", "Remove School");
        return "remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSchoolForm(@RequestParam ArrayList<String> school) {

        for (String aSchool : school) {
            schools.remove(aSchool);
        }

        return "redirect:";
    }

    @RequestMapping(value = "survey", method = RequestMethod.GET)
    public String displaySchoolSurveyForm(Model model) {

        model.addAttribute("schools", schools);
        model.addAttribute("title", "Survey");
        return "survey";
    }

    @RequestMapping(value = "survey", method = RequestMethod.POST)
    public String processSchoolSurveyForm(Model model, @RequestParam String schoolType, String publicSchool,
                                          String privateReligious, String privateCatholic,
                                          String privateIndependent, String football, String basketball,
                                          String soccer, String tennis, String notes) {

        model.addAttribute("preferences", "Your Preferences");

        return "redirect:";


    }

}
