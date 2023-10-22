package com.example.car.dto.response;

import lombok.*;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CarDTO {
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String date;
}
