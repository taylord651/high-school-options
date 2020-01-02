package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.models.*;
import com.launchcode.highschooloptions.models.data.SchoolDao;
import com.launchcode.highschooloptions.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Domonique Taylor
 */

@RequestMapping("school")
@Controller
public class HighSchoolOptionsContoller {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private UserDao userDao;

    // Request path: / (index template (homepage))
    @RequestMapping (value = "")
    public String index(Model model) {

        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("title", "High School Options");

        return "school/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model) {

        model.addAttribute("title", "Add School");
        model.addAttribute(new School());
        model.addAttribute("schoolTypes", SchoolType.values());
        model.addAttribute("schoolGpas", SchoolGpa.values());
        model.addAttribute("schoolMaps", SchoolMap.values());
        model.addAttribute("schoolSpecialties", SchoolSpecialty.values());
        return "school/add";

    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddForm(@ModelAttribute @Valid School newSchool,
                                 Errors errors, Model model, @RequestParam String website) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add School");
            model.addAttribute("schoolTypes", SchoolType.values());
            model.addAttribute("schoolGpas", SchoolGpa.values());
            model.addAttribute("schoolMaps", SchoolMap.values());
            model.addAttribute("schoolSpecialties", SchoolSpecialty.values());
            return "school/add";
        // } else if (!website.contains("https://") || !website.contains("http://")) {
            //website = ("http://" + website);
            // schoolDao.save(newSchool);

        } else {
            schoolDao.save(newSchool);
        }

        return "redirect:";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSchoolForm(Model model) {
        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("title", "Remove School");
        return "school/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSchoolForm(@RequestParam int[] schoolIds) {

        for (int schoolId : schoolIds) {
            schoolDao.deleteById(schoolId);
        }

        return "redirect:";
    }


}
