package com.example.car.controller;

import com.example.car.config.AuthEntryPointJwt;
import com.example.car.config.JwtService;
import com.example.car.dto.response.CarDTO;
import com.example.car.model.entity.Car;
import com.example.car.repository.UserRepository;
import com.example.car.service.CarService;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    private final CarService carService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @GetMapping("/list")
    private ResponseEntity<List<CarDTO>> home(
/*            @RequestParam("_start") int pageStart,
            @RequestParam("_end") int pageEnd,
            @RequestParam(value = "_sort", required = false) String sort,
            @RequestParam(value = "_order",required = false) String order*/

    ){
        List<CarDTO> deneme= new ArrayList<>();
        var dto= CarDTO.builder()
                .id(1L)
                .name("name")
                .brand("brand")
                .model("model")
                .date("date")
                .build();

        deneme.add(dto);
        deneme.add(dto);
        deneme.add(dto);
        deneme.add(dto);
        return ResponseEntity.ok(deneme);
    }

    @GetMapping
    private ResponseEntity<List<Car>> cars(){
        return ResponseEntity.ok(carService.getAll());
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<HttpStatus> delete(@PathVariable String id){
        return ResponseEntity.ok(carService.deleteOne(Long.valueOf(id)));
    }
    @PutMapping()
    private ResponseEntity<HttpStatus> update(
        //    @RequestParam("username") String username,
            @RequestBody Car car  ){
        return ResponseEntity.ok(carService.update(car,"remzisahbaz"));
    }

    @GetMapping("/{id}")
    private ResponseEntity<Car> car(@PathVariable String id){
        return ResponseEntity.ok(carService.getOneCarById(Long.valueOf(id)));
    }

    @PostMapping()
    //@RolesAllowed("USER")
    private ResponseEntity<CarDTO> carRegister(@RequestBody Car car ,
                                               @RequestHeader(name="Authorization") String token) {
        var username= jwtService.extractUsername(token.substring(7));
   /*     if(jwtService.isTokenValid(token,userRepository.findByUsername(username).get()))
        {
            logger.error("Valid token");

        }*/
        return ResponseEntity.ok(carService.register(car,username));
    }

}
