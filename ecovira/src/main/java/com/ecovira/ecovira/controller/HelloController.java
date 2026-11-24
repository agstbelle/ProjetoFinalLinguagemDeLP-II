package com.ecovira.ecovira.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController 
public class HelloController {
    @GetMapping("/ola")
    public String ola() {
        return "Olá mundo!!";
    }
    
}
