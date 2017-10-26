package org.launchcode.controllers;

import org.launchcode.models.Puppy;
import org.launchcode.models.User;
import org.launchcode.models.data.PuppyDao;
import org.launchcode.models.data.UserDao;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import javax.validation.Valid;


@Controller
@RequestMapping("puppy")
public class PuppyController {


    @Autowired
    private PuppyDao puppyDao;
    @Autowired
    private UserDao userDao;

    // Request path: /puppy
    @CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("puppies", puppyDao.findAll());
        model.addAttribute("title", "My Puppies");

        return "puppy/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPuppyForm(Model model) {
        model.addAttribute("title", "Add Your Puppy");
        model.addAttribute(new Puppy());
        model.addAttribute("users", userDao.findAll());
        return "puppy/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPuppyForm(@ModelAttribute @Valid Puppy newPuppy,
                                      Errors errors, @RequestParam int userId,
                                      Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Your Puppy");
            model.addAttribute("users", userDao.findAll());
            return "puppy/add";
        }

        User user = userDao.findOne(userId);
        newPuppy.setUser(user);
        puppyDao.save(newPuppy);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePuppyForm(Model model) {
        model.addAttribute("puppies", puppyDao.findAll());
        model.addAttribute("title", "Remove Your Puppy");
        return "puppy/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePuppyForm(@RequestParam int[] puppyIds) {

        for (int puppyId : puppyIds) {
            puppyDao.delete(puppyId);
        }

        return "redirect:";
    }

}