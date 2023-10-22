package com.example.car.controller;

import com.example.car.config.JwtService;
import com.example.car.dto.request.UserUpdatePasswordRequest;
import com.example.car.dto.response.UserResponse;
import com.example.car.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;


    @GetMapping("/home")
    private ResponseEntity<String> home() {
        return ResponseEntity.ok("USER controller");
    }

    @GetMapping("/details")
    private ResponseEntity<UserResponse> userDetails(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(userService.getUserDetails(jwtService.extractUsername(token)));
    }

    @PatchMapping("/update")
    private ResponseEntity<?> userUpdate(
            @RequestBody UserUpdatePasswordRequest userUpdatePasswordRequest,
            @RequestHeader("Authorization") String bearerToken,
            Principal connectedUser) {

        String jwtToken = bearerToken.split(" ")[1].trim();
        String username= jwtService.extractUsername(jwtToken);
        Claims claims=jwtService.extractAllClaims(jwtToken);

        return
                userService.update(username
                        ,userUpdatePasswordRequest
                        , connectedUser

                );
    }


}