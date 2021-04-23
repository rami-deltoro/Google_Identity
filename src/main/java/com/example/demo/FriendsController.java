package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Slf4j
@Controller
public class FriendsController {
    @GetMapping("/friends")
    public String friendForm(Model model) {
        model.addAttribute("personForm", new Person());
        return "friendsForm";
    }

    @PostMapping("/friends")
    public String submissionResult(@ModelAttribute("personForm") Person person) {
        log.info("Got friend {}",person);
        return "result";
    }
}
