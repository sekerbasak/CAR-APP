package com.example.car.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

import java.io.Serializable;

@Data
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "cars")
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "uniqueCar", columnNames = {"name","brand","model","date","user_id"})})
public class Car implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String brand;
    private String model;
    private String date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",referencedColumnName = "id",unique = false)
    @JsonBackReference
    @NonNull
    private User user;
}
