package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.models.*;
import com.launchcode.highschooloptions.models.data.SchoolDao;
import com.launchcode.highschooloptions.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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
    @RequestMapping (value = "", method = RequestMethod.GET)
    public String index(Model model, HttpSession session) {

        model.addAttribute("schools", schoolDao.findAll());
        model.addAttribute("title", "High School Options");

        if (session.getAttribute("username") == null) {
            return "school/index";
        } else {
            model.addAttribute("welcome_user", "Welcome " + session.getAttribute("username"));
            return "school/index";
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String addOptionFromHome (Model model, HttpSession session, @ModelAttribute
                                     @RequestParam int[] schoolIds) {

        if (session.getAttribute("username") == null) {
            model.addAttribute("add_error", "You must login to add a school to your account");
            model.addAttribute("schools", schoolDao.findAll());

            return "school/index";
        } else {

            for (int schoolId : schoolIds) {
                School theSchool = schoolDao.findById(schoolId);
                User theUser = userDao.findByName(session.getAttribute("username").toString());
                theUser.addItem(theSchool);
                userDao.save(theUser);
            }

            return "redirect:/myschools";
        }

    }
}