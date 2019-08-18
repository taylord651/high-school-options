package com.launchcode.highschooloptions.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Domonique Taylor
 */

@Controller
@RequestMapping("options")
public class HighSchoolOptionsContoller {

    // Request path: /options
    @RequestMapping (value = "")
    public String index(Model model) {

        model.addAttribute("title", "High School Options");

        return "options/index";
    }


}
