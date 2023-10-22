package com.example.car.dto.request;

import com.example.car.model.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarRegister {
    private String name;
    private String brand;
    private String model;
    private String date;
    private Long userId;
}
