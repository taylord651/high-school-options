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
    public String processLoginForm (Model model, @ModelAttribute @Valid User user,
                                    @RequestParam String name,
                                    String password, HttpServletRequest request) {

        @SuppressWarnings("unchecked")

        //userDao.findAll();
        User login_user = userDao.findByName(name);

        boolean valid_username = name.length() > 3;
        boolean valid_password = password.length() > 3;
        boolean verified_password = password.equals(login_user.getPassword());
        boolean verified_user = login_user != null;

        if (!valid_username || !valid_password || !verified_user || !verified_password) {
            if (!valid_username) {
                model.addAttribute("missing_username_error", "Username required");
            } else if (!verified_user) {
                model.addAttribute("invalid_username_error", "Username not found. Create an account.");
            } if (!valid_password) {
                model.addAttribute("missing_password_error", "Password required");
            } else if (!password.equals(login_user.getPassword())) {
                model.addAttribute("incorrect_password_error", "Incorrect password");
            }

            model.addAttribute("username", name);
            model.addAttribute("title", "Login");
            return "redirect:";
        } else {

            HttpSession session = request.getSession();
            session.setAttribute("username", name);

            return "redirect:/school";
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

    @RequestMapping(value = "all-users", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("title", "Users");
        model.addAttribute("users", userDao.findAll());
        return "user/all-users";
    }

    @RequestMapping(value = "view/{userId}", method = RequestMethod.GET)
    public String viewMenu (Model model, @PathVariable int userId) {

        User user = userDao.findById(userId);

        model.addAttribute("title", "My Schools: " + user.getName());
        model.addAttribute("schools", user.getSchools());
        model.addAttribute("userId", user.getId());

        return "user/view";
    }

    @RequestMapping(value = "add-school/{userId}", method = RequestMethod.GET)
    public String addOption (Model model, @PathVariable int userId) {

        User user = userDao.findById(userId);

        AddSchoolOptionForm form = new AddSchoolOptionForm(
                schoolDao.findAll(),
                user);

        model.addAttribute("title", "Add School to My Account: " + user.getName());
        model.addAttribute("form", form);
        return "user/add-school";
    }

    @RequestMapping(value = "add-school", method = RequestMethod.POST)
    public String addOption (Model model, @ModelAttribute @Valid AddSchoolOptionForm form, Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("form", form);
            return "user/add-school";
        }

        School theSchool = schoolDao.findById(form.getSchoolId());
        User theUser = userDao.findById(form.getUserId());
        theUser.addItem(theSchool);
        userDao.save(theUser);

        return "redirect:/user/view/" + theUser.getId();
    }

    @RequestMapping (value = "/logout")
    public String logout(Model model, HttpServletRequest request, HttpSession session) {
        session.invalidate();
        //model.addAttribute("logout_successful", "Logout successful");
        return "redirect:/school";
    }

    }

