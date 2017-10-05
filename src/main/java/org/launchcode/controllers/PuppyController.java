package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("puppy")
public class PuppyController {

    @RequestMapping(value="viewpuppyprofile")
    public String displayPuppyView(){

        return "puppy/viewpuppyprofile";
    }
}