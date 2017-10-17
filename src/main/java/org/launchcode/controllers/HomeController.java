package org.launchcode.controllers;

import org.launchcode.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("home")
public class HomeController extends AbstractController {

    @RequestMapping("")
    public String displayHomePage(Model model, HttpServletRequest request){

        User user = getUserFromSession(request.getSession());

        model.addAttribute("User", user.getName());
        model.addAttribute("title", "Home Page");
        model.addAttribute("puppies", user.getPuppies());
        model.addAttribute("welcome", "Welcome " + user.getName() + "!");
        model.addAttribute("sessionActive", isSessionActive(request.getSession()));

        return "home/index";
    }

    @RequestMapping("viewpups")
    public String displayViewPupsPage(Model model){
        return "redirect:/puppy/index";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){

        clearSession(request.getSession());

        return "redirect:/authenticate";
    }

}
