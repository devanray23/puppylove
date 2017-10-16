package org.launchcode.controllers;

import org.launchcode.models.User;
import org.launchcode.models.Puppy;
import org.launchcode.models.data.UserDao;
import org.launchcode.models.data.PuppyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("puppy")
public class PuppyController {


    @Autowired
    private PuppyDao puppyDao;
    @Autowired
    private UserDao userDao;

    // Request path: /puppy
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
        return "puppy/view";
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

    @RequestMapping(value = "edit/{puppyId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int puppyId) {
        model.addAttribute("puppy", puppyDao.findOne(puppyId));
        puppyDao.delete(puppyId);
        return "puppy/edit";
    }

    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String processEditForm (@ModelAttribute  @Valid Puppy tempPuppy,
                                   Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit Puppy");
            model.addAttribute("puppy", tempPuppy);
            puppyDao.delete(tempPuppy.getId());
            return "puppy/edit";
        }

        puppyDao.save(tempPuppy);
        return "redirect:";
    }

    @RequestMapping(value = "view/{puppyId}", method = RequestMethod.GET)
    public String viewPuppy(Model model, @PathVariable int puppyId) {
        model.addAttribute("puppy", puppyDao.findOne(puppyId));
        return "puppy/view";
    }
}