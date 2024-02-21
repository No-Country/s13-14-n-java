package com.latam.companerosDeViajeAPI.controller.user;

import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.dto.user.UserUpdateDto;
import com.latam.companerosDeViajeAPI.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping
    public ResponseEntity<UserDto> getUserDetails(){
        return ResponseEntity.ok(userService.getUserDetails());
    }
    @PutMapping("/edit")
    @Transactional
    public ResponseEntity<UserDto> updateUser(@RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.updateUser(userUpdateDto));
    }

}
