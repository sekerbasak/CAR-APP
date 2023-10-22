package com.example.car.dto.response;

import com.example.car.model.entity.Car;
import com.example.car.model.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.util.Set;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    @Enumerated(EnumType.STRING)
    private Role role;
    private Set<Car> cars;
}
