package com.example.car.service;

import com.example.car.config.JwtService;
import com.example.car.dto.request.RegisterRequest;
import com.example.car.dto.request.UserUpdatePasswordRequest;
import com.example.car.dto.response.AuthenticationResponse;
import com.example.car.dto.response.UserResponse;
import com.example.car.model.entity.User;
import com.example.car.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public UserResponse getUserDetails(String username) {

        var user = userRepository.findByUsername(username);

        if (user.isPresent()) {
            return UserResponse.builder()
                    .username(user.get().getUsername())
                    .firstname(user.get().getFirstname())
                    .lastname(user.get().getLastname())
                    .email(user.get().getEmail())
                    .role(user.get().getRole())
                    .build();
        }
        ;
        return null;
    }

    public ResponseEntity<?> update(String username, UserUpdatePasswordRequest userUpdate, Principal connectedUser) {

        var getUser =userRepository.findByUsername(username).get();// (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        var getUserOldPassword= getUser.getPassword();


        if (!userUpdate.getNewPassword().equals(userUpdate.getNewPasswordVerify())) {
            return  ResponseEntity.ofNullable("şifreler uyuşmuyor");
        }
        if (!userRepository.existsByUsername(getUser.getUsername())) {
            return  ResponseEntity.ofNullable("kullanıcı hatalı");
        }
        if (passwordEncoder.matches(passwordEncoder.encode(userUpdate.getOldPassword()), getUser.getPassword())) {
            return ResponseEntity.ofNullable("şifreler uyuşmuyor");
        }
        getUser.setPassword(passwordEncoder.encode(userUpdate.getNewPassword()));
        userRepository.save(getUser);
        SecurityContextHolder.clearContext();
        String jwt=jwtService.generateToken(getUser);

/*        SecurityContextHolder.clearContext();
        AuthenticationRequest auth = AuthenticationRequest.builder()
                .username(username)
                .password(userUpdate.getNewPassword())
                .build();
        authenticationService.authenticate(auth);*/
        return ResponseEntity.ok( AuthenticationResponse.builder().token(jwt).build());
    }
}
