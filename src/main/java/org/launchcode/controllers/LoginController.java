package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.forms.LoginForm;
import org.launchcode.models.forms.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("authenticate")
public class LoginController extends AbstractController {

    //Displays Login Page
    @RequestMapping(value= "", method = RequestMethod.GET)
    public String displayLoginPage(Model model, HttpServletRequest request){
        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Login");
        model.addAttribute("loginForm", new LoginForm());
        return "authenticate/index";
    }

    //Displays Register Page
    @RequestMapping(value="register", method = RequestMethod.GET)
    public String displayRegisterForm(Model model, HttpServletRequest request){
        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Register");
        model.addAttribute("registerForm", new RegisterForm());

        return "authenticate/register";
    }

    //Processes Register Form and validates user input
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute @Valid RegisterForm registerForm, Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Register to RandNums");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            return "authenticate/register";
        }

        User existingUser = userDao.findByEmail(registerForm.getEmail());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            return "authenticate/register";
        }

        User newUser = new User(registerForm.getEmail(), registerForm.getEmail(), registerForm.getPassword());

        userDao.save(newUser);
        setUserInSession(request.getSession(), newUser);

        return "redirect:/home";
    }

    //Processes Login Form and validates user input
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String login(@ModelAttribute @Valid LoginForm loginForm, Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Login");
            return "authenticate/index";
        }

        User theUser = userDao.findByEmail(loginForm.getEmail());
        String password = loginForm.getPassword();

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            model.addAttribute("title", "Login");
            return "authenticate/index";
        }

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            model.addAttribute("title", "Login");
            return "authenticate/index";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/home";
    }
}
