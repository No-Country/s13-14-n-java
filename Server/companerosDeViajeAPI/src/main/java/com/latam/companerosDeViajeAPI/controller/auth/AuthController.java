package com.latam.companerosDeViajeAPI.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    public String login(){
        return "login";
    }
    public String register(){
        return "register";
    }

}
