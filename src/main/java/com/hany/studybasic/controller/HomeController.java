package com.hany.studybasic.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    @GetMapping(value = {"", "/"})
    public String home(Model model) {
          model.addAttribute("data", "장한영");
        return "home";
    }
}
