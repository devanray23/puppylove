package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.forms.EditForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("home")
public class HomeController extends AbstractController {

    // display user homepage
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

    //redirect to a user's puppies
    @RequestMapping("viewpups")
    public String displayViewPupsPage(Model model){

        return "redirect:/puppy/index";
    }

    // logout
    @RequestMapping("logout")
    public String logout(HttpServletRequest request){

        clearSession(request.getSession());

        return "redirect:/authenticate";
    }

    // display public view of user's profile
    @RequestMapping(value = "viewprofile", method = RequestMethod.GET)
    public String viewProfile(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("puppies", user.getPuppies());
        model.addAttribute("user", user);
        return "home/viewprofile";
    }

    // display edit profile form
    @RequestMapping(value = "editprofile", method = RequestMethod.GET)
    public String displayEditForm(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        EditForm editForm = new EditForm();
        editForm.setName(user.getName());
        editForm.setEmail(user.getEmail());
        editForm.setDescription(user.getDescription());
        model.addAttribute("editForm", editForm);
        return "home/editprofile";
    }

    // process edit profile form
    @RequestMapping(value = "editprofile", method = RequestMethod.POST)
    public String processEditForm (@ModelAttribute @Valid EditForm tempUser,
                                   Errors errors, Model model,
                                   HttpServletRequest request) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Your Profile");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            model.addAttribute("editForm", tempUser);

            return "home/editprofile";
        }

        model.addAttribute("title", "Edit Your Profile");
        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        User user = getUserFromSession(request.getSession());
        user.setName(tempUser.getName());
        user.setEmail(tempUser.getEmail());
        user.setDescription(tempUser.getDescription());
        userDao.save(user);

        return "redirect:";
    }

}
