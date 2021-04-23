package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Slf4j
@Controller
public class LoginController {

    private final GoogleAuthenticationVerifier googleAuthenticationVerifier;

    @GetMapping("/login")
    public String friendForm(Model model) {
        return "login";
    }

    @PostMapping(value = "/tokensignin" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public String submissionResult(@RequestBody  JsonNode jsonNode) {
        log.info("Got token sign in {}",jsonNode.toPrettyString());
        googleAuthenticationVerifier.verify(jsonNode);
        return "result";
    }

}
