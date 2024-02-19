package com.latam.companerosDeViajeAPI.controller.user;

import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserDto> getUserDetails(){
        return ResponseEntity.ok(userService.getUserDetails());
    }
}
