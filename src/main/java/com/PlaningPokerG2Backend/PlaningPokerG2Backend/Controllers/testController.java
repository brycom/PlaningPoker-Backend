package com.PlaningPokerG2Backend.PlaningPokerG2Backend.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class testController {

    @GetMapping("/")
    public String test() {
        return "test";
    }
}
