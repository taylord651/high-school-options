package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.models.AddSchoolOptionForm;
import com.launchcode.highschooloptions.models.School;
import com.launchcode.highschooloptions.models.User;
import com.launchcode.highschooloptions.models.data.SchoolDao;
import com.launchcode.highschooloptions.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequestMapping("myschools")
public class MySchoolsController {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayMySchools(Model model, HttpSession session) {

        //System.out.println(session.getAttribute("username"));

        if (session.getAttribute("username") == null) {
            //model.addAttribute("login_message", "You must login to access your account");
            return "redirect:/user";
        } else {

            User user = userDao.findByName(session.getAttribute("username").toString());

            model.addAttribute("title", "My Schools: " + user.getName());
            model.addAttribute("schools", user.getSchools());
            model.addAttribute("userId", user.getId());

            model.addAttribute("title", "My Account: " + session.getAttribute("username"));
            //model.addAttribute("user", user);
            //session.getId();

            return "myschools/index";

        }
    }

    @RequestMapping(value = "/add-school", method = RequestMethod.GET)
    public String addOption (Model model, HttpSession session) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        AddSchoolOptionForm form = new AddSchoolOptionForm(
                schoolDao.findAll(),
                user);

        model.addAttribute("title", "Add School to My Account: " + user.getName());
        model.addAttribute("form", form);
        return "myschools/add-school";
    }

    @RequestMapping(value = "/add-school", method = RequestMethod.POST)
    public String addOption (Model model, @ModelAttribute @Valid AddSchoolOptionForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "myschools/add-school";
        }

        School theSchool = schoolDao.findById(form.getSchoolId());
        User theUser = userDao.findById(form.getUserId());
        theUser.addItem(theSchool);
        userDao.save(theUser);

        return "redirect:/myschools";
    }
}
