package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.forms.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String displaySearchPage(Model model, HttpServletRequest request){

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Search Users");
        model.addAttribute("users", userDao.findAll());
        model.addAttribute(new SearchForm());


        return "home/search";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String processSearchForm(@ModelAttribute @Valid SearchForm searchForm, Model model, Errors errors,
                                    HttpServletRequest request){

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Search Users");
        model.addAttribute(new SearchForm());

        if(errors.hasErrors()){
            model.addAttribute("users", userDao.findAll());
            return "home/search";
        }

        model.addAttribute("users", userDao.findByLocation(searchForm.getLocation()));

        return "home/search";
    }

    @RequestMapping("logout")
    public String logout(HttpServletRequest request){

        clearSession(request.getSession());

        return "redirect:/authenticate";
    }

}
