package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.models.*;
import com.launchcode.highschooloptions.models.data.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by Domonique Taylor
 */

@Controller
public class HighSchoolOptionsContoller {

    @Autowired
    private SchoolDao schoolDao;

    // Request path: / (index template (homepage))
    @RequestMapping (value = "")
    public String index(Model model) {

        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("title", "High School Options");

        return "index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {

        model.addAttribute("title", "Add School");
        model.addAttribute(new School());
        model.addAttribute("schoolTypes", SchoolType.values());
        model.addAttribute("schoolGpas", SchoolGpa.values());
        model.addAttribute("schoolMaps", SchoolMap.values());
        model.addAttribute("schoolSpecialties", SchoolSpecialty.values());
        return "add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid School newSchool,
                                 Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add School");
            model.addAttribute("schoolTypes", SchoolType.values());
            model.addAttribute("schoolGpas", SchoolGpa.values());
            model.addAttribute("schoolMaps", SchoolMap.values());
            model.addAttribute("schoolSpecialties", SchoolSpecialty.values());
            return "add";
        }

        schoolDao.save(newSchool);

        // Redirect to /
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSchoolForm(Model model) {
        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("title", "Remove School");
        return "remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSchoolForm(@RequestParam int[] schoolIds) {

        for (int schoolId : schoolIds) {
            schoolDao.deleteById(schoolId);
        }

        return "redirect:";
    }

    @RequestMapping(value = "survey", method = RequestMethod.GET)
    public String displaySchoolSurveyForm(Model model) {

        model.addAttribute("schools", schoolDao.findAll());
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
