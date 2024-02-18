package com.latam.companerosDeViajeAPI.controller.auth;

import com.latam.companerosDeViajeAPI.dto.auth.AuthResponseDto;
import com.latam.companerosDeViajeAPI.dto.auth.LoginRequestDto;
import com.latam.companerosDeViajeAPI.dto.auth.RegisterRequestDto;
import com.latam.companerosDeViajeAPI.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }
    @PostMapping(value = "register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequestDto){
        return  ResponseEntity.ok(authService.register(registerRequestDto));
    }
}
