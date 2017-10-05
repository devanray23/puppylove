package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login")
public class LoginController {

    @RequestMapping(value = "")
    public String displayLoginPage(Model model){
        return "login";
    }

    @RequestMapping(value = "register")
    public String displayRegisterPage(Model model){
        return "register";
    }
}
