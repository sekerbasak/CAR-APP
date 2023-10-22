package com.example.car.controller;

import com.example.car.dto.request.AuthenticationRequest;
import com.example.car.dto.request.RegisterRequest;
import com.example.car.dto.response.AuthenticationResponse;
import com.example.car.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest requestBody
    ){
        return ResponseEntity.ok(authService.register(requestBody));
    }

    @PostMapping("/authenticate")
    public ResponseEntity <AuthenticationResponse> register(
            @RequestBody AuthenticationRequest authRequest
    ){
        return ResponseEntity.ok(authService.authenticate(authRequest));
    }
}