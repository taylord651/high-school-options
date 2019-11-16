package com.launchcode.highschooloptions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Domonique Taylor
 */

@Controller
public class HighSchoolOptionsContoller {

    static ArrayList<String> school_names = new ArrayList<>();


    // Request path: / (index template (homepage))
    @RequestMapping (value = "")
    public String index(Model model) {

        model.addAttribute("school_names", school_names);
        model.addAttribute("title", "High School Options");

        return "index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {

        model.addAttribute("title", "Add School");
        return "add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@RequestParam String schoolName, Model model) {

        //
        school_names.add(schoolName);

        // Redirect to /
        return "redirect:";
    }

    @RequestMapping(value = "survey", method = RequestMethod.GET)
    public String displaySchoolSurveyForm(Model model) {

        model.addAttribute("title", "School Options Survey");
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
