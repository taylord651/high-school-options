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
@RequestMapping("user")
public class UserController {

    @Autowired
    SchoolDao schoolDao;

    @Autowired
    UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String displayLoginForm (Model model) {

        model.addAttribute("title", "Login");

        return "user/index";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String processLoginForm (Model model, @ModelAttribute User user,
                                    @RequestParam String name,
                                    String password, HttpServletRequest request) {

        User login_user = userDao.findByName(name);

        boolean verified_user = login_user != null;
        boolean valid_username = name.length() > 3;
        boolean valid_password = password.length() > 3;

        if (!valid_username || !valid_password || !verified_user || !password.equals(login_user.getPassword())) {
            if (!valid_username) {
                model.addAttribute("missing_username_error", "Username required");
            } else if (!verified_user) {
                model.addAttribute("invalid_username_error", "Username not found. Create an account.");
            } if (!valid_password) {
                model.addAttribute("missing_password_error", "Password required");
            } else if (verified_user && !password.equals(login_user.getPassword())) {
                model.addAttribute("incorrect_password_error", "Incorrect password");
            }

                model.addAttribute("name", name);
                model.addAttribute("title", "Login");
                return "/user/index";
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("username", name);

            return "redirect:/myschools";
        }
    }

    /* if (role.equals("Admin")) {
            Only allow access/show "home", "add", "remove" and "logout" pages;
            redirect to /school
       } else {
            Only allow access/show "home", "logout" and "my schools" pages;
            redirect to /school
       }
     */

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

                return "redirect:/user";
            }
        }

    @RequestMapping (value = "/logout")
    public String logout(Model model, HttpServletRequest request, HttpSession session) {
        session.invalidate();
        //model.addAttribute("logout_successful", "Logout successful");
        return "redirect:/school";
    }

    }

