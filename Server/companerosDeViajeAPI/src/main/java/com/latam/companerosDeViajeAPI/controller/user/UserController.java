package com.latam.companerosDeViajeAPI.controller.user;

import com.latam.companerosDeViajeAPI.dto.notification.NotificationDto;
import com.latam.companerosDeViajeAPI.dto.user.UserDto;
import com.latam.companerosDeViajeAPI.dto.user.UserUpdateDto;
import com.latam.companerosDeViajeAPI.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.updateUser(userUpdateDto));
    }
    @GetMapping("/notificationCount")
    public ResponseEntity<Integer> getUnreadNotificationCount(){
        return ResponseEntity.ok(userService.GetNotificationCount());
    }
    @GetMapping("/notification")
    public ResponseEntity<Page<NotificationDto>> getAllNotificationByUser(Pageable pageable){
        return  ResponseEntity.ok(userService.getAllNotificationByUser(pageable));
    }




}
