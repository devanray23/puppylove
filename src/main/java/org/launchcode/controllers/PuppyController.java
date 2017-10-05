package org.launchcode.controllers;

import org.launchcode.models.Puppy;
import org.launchcode.models.PuppyData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.awt.*;

@Controller
@RequestMapping("puppy")
public class PuppyController {

    // Request path: /cheese
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("puppies", PuppyData.getAll());
        model.addAttribute("title", "My Puppies");

        return "puppy/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddPuppyForm(Model model) {
        model.addAttribute("title", "Add Your Puppy");
        return "puppy/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddPuppyForm(@ModelAttribute Puppy newPuppy) {
        PuppyData.add(newPuppy);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemovePuppyForm(Model model) {
        model.addAttribute("puppies", PuppyData.getAll());
        model.addAttribute("title", "Remove Puppy");
        return "puppy/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemovePuppyForm(@RequestParam int[] puppyIds) {

        for (int puppyId : puppyIds) {
            PuppyData.remove(puppyId);
        }

        return "redirect:";
    }


    @RequestMapping(value = "edit/{puppyId}", method = RequestMethod.GET)
    public String displayEditForm(Model model, @PathVariable int puppyId) {

        Puppy thePuppy = PuppyData.getById(puppyId);
        model.addAttribute("puppy", thePuppy);
        return "puppy/edit";
    }

    @RequestMapping(value = "edit/{puppyId}", method = RequestMethod.POST)
    public String processEditForm(@PathVariable int puppyId, int age,
                                  String name, String breed, Image photo, String location) {

        Puppy thePuppy = PuppyData.getById(puppyId);
        thePuppy.setName(name);
        thePuppy.setBreed(breed);
        thePuppy.setLocation(location);
        thePuppy.setPhoto(photo);

        return "redirect:";
    }
}