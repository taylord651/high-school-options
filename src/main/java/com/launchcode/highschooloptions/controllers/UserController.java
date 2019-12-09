package com.launchcode.highschooloptions.controllers;

import com.launchcode.highschooloptions.forms.User;
import com.launchcode.highschooloptions.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm (Model model) {

        model.addAttribute("title", "Login");

        return "user/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm (Model model, @ModelAttribute @Valid User user, Errors errors,
                                    @RequestParam String name, String password) {

        //userDao.findAll();
        User login_user = userDao.findByName(name);

        boolean valid_username = name.length() >= 1;
        boolean valid_password = password.length() >= 1;

        if (!valid_username) {
            model.addAttribute("title", "Login");
            model.addAttribute("error_message", "Username required");
            return "user/index";
        } else if (!valid_password) {
            model.addAttribute("title", "Login");
            model.addAttribute("error_message", "Password required");
            return "user/index";
        } else if (login_user == null) {
            model.addAttribute("title", "Login");
            model.addAttribute("username_error", "Username not found. Create an account.");
            return "user/index";
        } else if (!password.equals(login_user.getPassword())) {
            model.addAttribute("title", "Login");
            model.addAttribute("password_error", "Incorrect password");
            return "user/index";
        } else {
            return "redirect:/school";
        }
    }

    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String displayNewUserForm (Model model) {

        model.addAttribute("title", "Create an Account");

        model.addAttribute(new User());

        return "user/new";
    }

    @RequestMapping(value = "new", method = RequestMethod.POST)
    public String processNewUserFrom (Model model, @ModelAttribute @Valid User user, Errors errors,
                                      @RequestParam String password, String verifyPassword) {

        if (!password.equals(verifyPassword)) {
            model.addAttribute("title", "Create an Account");
            model.addAttribute("error_message", "Password and Verify Password must match");
            return "user/new";
        } else if (errors.hasErrors()) {
            model.addAttribute("title", "Create an Account");
            return "user/new";
        } else {
                userDao.save(user);

                return "redirect:";
            }
        }
    }

