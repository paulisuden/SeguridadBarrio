package com.landing.landing.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LandingController {

    @GetMapping("/")
    public String enter() {
        return "redirect:/landing";
    }

    @GetMapping("/landing")
    public String landing(Model model) {
        model.addAttribute("pageTitle", "Learner - Home");
        return "index.html";
    }
}
