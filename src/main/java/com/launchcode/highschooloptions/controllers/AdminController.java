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

@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private UserDao userDao;

    // Request path: / (index template (homepage))
    @RequestMapping (value = "")
    public String index(Model model, HttpSession session) {

        if (session.getAttribute("username") == null) {
            return "redirect:/user";
        }

        User user = userDao.findByName(session.getAttribute("username").toString());

        if (user.getRole().equals("Admin")){
            model.addAttribute("welcome_user", "Welcome " + session.getAttribute("username"));
            model.addAttribute("title", "Admin Account: " + session.getAttribute("username"));
            return "admin/index";
        } else {
            return "redirect:/school";
        }
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddForm(Model model, HttpSession session) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        if (user.getRole().equals("Admin")) {
            model.addAttribute("title", "Add School");
            model.addAttribute(new School());
            model.addAttribute("schoolTypes", SchoolType.values());
            model.addAttribute("schoolGpas", SchoolGpa.values());
            model.addAttribute("schoolMaps", SchoolMap.values());
            model.addAttribute("schoolSpecialties", SchoolSpecialty.values());
            model.addAttribute("schoolGenders", SchoolGender.values());
            return "admin/add";
        } else {
            return "redirect:/school";
        }
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
            model.addAttribute("schoolGenders", SchoolGender.values());
            return "admin/add";
            // } else if (!website.contains("https://") || !website.contains("http://")) {
            //website = ("http://" + website);
            // schoolDao.save(newSchool);

        } else {
            schoolDao.save(newSchool);
        }

        return "redirect:/school";
    }


    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveSchoolForm(Model model, HttpSession session) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        if (user.getRole().equals("Admin")) {
            model.addAttribute("schools", schoolDao.findAll());
            model.addAttribute("title", "Remove School");
            return "admin/remove";
        } else {
            return "redirect:/school";
        }
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveSchoolForm(@RequestParam int[] schoolIds) {

        for (int schoolId : schoolIds) {
            schoolDao.deleteById(schoolId);
        }

        return "redirect:/school";
    }

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public String allUsers(Model model, HttpSession session) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        if (user.getRole().equals("Admin")) {
            model.addAttribute("title", "Users");
            model.addAttribute("users", userDao.findAll());
            return "admin/all-users";
        } else {
        return "redirect:/school";
    }
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String viewMenu (Model model, HttpSession session, @PathVariable int userId) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        User userById = userDao.findById(userId);

        if (user.getRole().equals("Admin")) {
            model.addAttribute("title", "My Schools: " + userById.getName());
            model.addAttribute("schools", userById.getSchools());
            model.addAttribute("userId", userById.getId());

            return "admin/view";
        } else {
            return "redirect:/school";
        }
    }

    @RequestMapping(value = "add-school/{userId}", method = RequestMethod.GET)
    public String addOption (Model model, HttpSession session, @PathVariable int userId) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        User userById = userDao.findById(userId);

        AddSchoolOptionForm form = new AddSchoolOptionForm(
                schoolDao.findAll(),
                userById);

        if (user.getRole().equals("Admin")) {
            model.addAttribute("title", "Add School to My Account: " + userById.getName());
            model.addAttribute("form", form);
            return "admin/add-school";
        } else {
            return "redirect:/school";
        }
        }

    @RequestMapping(value = "add-school", method = RequestMethod.POST)
    public String addOption (Model model, HttpSession session, @ModelAttribute @Valid AddSchoolOptionForm form, Errors errors) {

        User user = userDao.findByName(session.getAttribute("username").toString());

        if (!user.getRole().equals("Admin")) {
            return "redirect:/school";
        }

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "admin/add-school";
        }

        School theSchool = schoolDao.findById(form.getSchoolId());
        User theUser = userDao.findById(form.getUserId());
        theUser.addItem(theSchool);
        userDao.save(theUser);

        return "redirect:/admin/view/" + theUser.getId();
    }
}
