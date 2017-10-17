package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.Puppy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("puppy")
public class PuppyController extends AbstractController {

    // Request path: /puppy
    @RequestMapping(value = "")
    public String index(Model model, HttpServletRequest request) {

        User user = getUserFromSession(request.getSession());

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("puppies", user.getPuppies());
        model.addAttribute("title", "My Puppies");

        return "puppy/index";
    }


    // Change Puppy Adding for specific user
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPuppyForm(Model model, HttpServletRequest request) {

        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Add Your Puppy");
        model.addAttribute(new Puppy());
        model.addAttribute("users", userDao.findAll());
        return "puppy/add";
    }


    // Change Puppy Adding for specific user
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPuppyForm(@ModelAttribute @Valid Puppy newPuppy,
                                      Errors errors,
                                      Model model, HttpServletRequest request) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Your Puppy");
            model.addAttribute("sessionActive", isSessionActive(request.getSession()));
            model.addAttribute("users", userDao.findAll());
            return "puppy/add";
        }

        User user = userDao.findOne(getUserIdFromSession(request));
        newPuppy.setUser(user);
        puppyDao.save(newPuppy);
        return "redirect:";
    }

    // Change Puppy removing for specific user
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePuppyForm(Model model, HttpServletRequest request) {
        model.addAttribute("puppies", puppyDao.findAll());
        model.addAttribute("sessionActive", isSessionActive(request.getSession()));
        model.addAttribute("title", "Remove Your Puppy");
        return "puppy/remove";
    }

    // Change Puppy removing for specific user
    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePuppyForm(@RequestParam int[] puppyIds) {

        for (int puppyId : puppyIds) {
            puppyDao.delete(puppyId);
        }

        return "redirect:";
    }

}