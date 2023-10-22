package com.example.car.dto.request;

import com.example.car.model.entity.Role;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegister implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}