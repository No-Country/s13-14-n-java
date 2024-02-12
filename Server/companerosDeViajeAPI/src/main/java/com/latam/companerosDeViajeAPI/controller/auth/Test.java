package com.latam.companerosDeViajeAPI.controller.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class Test {
    @PostMapping
    public String test(){
        return "Token validado";
    }
}
