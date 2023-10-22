package com.example.car.service;


import com.example.car.config.JwtService;
import com.example.car.dto.request.AuthenticationRequest;
import com.example.car.dto.request.RegisterRequest;
import com.example.car.dto.response.AuthenticationResponse;
import com.example.car.model.entity.Role;
import com.example.car.model.entity.User;
import com.example.car.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncode;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthenticationResponse register(RegisterRequest requestBody) {
        var user = User.builder()
                .firstname(requestBody.getFirstname())
                .lastname(requestBody.getLastname())
                .email(requestBody.getEmail())
                .username(requestBody.getUsername())
                .password(passwordEncode.encode(requestBody.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwt).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        var user = userRepository.findByUsername(authRequest.getUsername()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
