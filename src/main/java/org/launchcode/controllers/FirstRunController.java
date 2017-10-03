package org.launchcode.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class FirstRunController {

    @RequestMapping(value = "")
    public String index(){
        return "index";
    }
}
