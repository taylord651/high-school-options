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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@RequestMapping("admin")
@Controller
public class AdminController {

    @Autowired
    private SchoolDao schoolDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "/all-users", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDao.findAll());
        return "admin/all-users";
    }

    @RequestMapping(value = "/view/{userId}", method = RequestMethod.GET)
    public String viewMenu (Model model, @PathVariable int userId) {

        User user = userDao.findById(userId);

        model.addAttribute("title", "My Schools: " + user.getName());
        model.addAttribute("schools", user.getSchools());
        model.addAttribute("userId", user.getId());

        return "admin/view";
    }

    @RequestMapping(value = "add-school/{userId}", method = RequestMethod.GET)
    public String addOption (Model model, @PathVariable int userId) {

        User user = userDao.findById(userId);

        AddSchoolOptionForm form = new AddSchoolOptionForm(
                schoolDao.findAll(),
                user);

        model.addAttribute("title", "Add School to My Account: " + user.getName());
        model.addAttribute("form", form);
        return "admin/add-school";
    }

    @RequestMapping(value = "add-school", method = RequestMethod.POST)
    public String addOption (Model model, @ModelAttribute @Valid AddSchoolOptionForm form, Errors errors) {

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
