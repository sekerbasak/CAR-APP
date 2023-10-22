package com.example.car.service;

import com.example.car.dto.response.CarDTO;
import com.example.car.model.entity.Car;
import com.example.car.repository.CarRepository;
import com.example.car.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public List<Car> getAll() {
        var cars = carRepository.findAll();
        if (cars.isEmpty()) return Collections.emptyList();
        return cars;
        /*        return cars.stream().map(
                        car -> CarDTO.builder()
                                .id(car.getId())
                                .name(car.getName())
                                .brand(car.getBrand())
                                .model(car.getModel())
                                .date(car.getDate())
                                .build())
                .collect(Collectors.toSet());*/
    }

    public CarDTO register(Car car, String username) {
        var user = userRepository.findByUsername(username);
        if (user.isPresent()) {
            var saved = carRepository.save(Car.builder()
                    .brand(car.getBrand())
                    .name(car.getName())
                    .model(car.getModel())
                    .date(car.getDate())
                    .user(user.get())
                    .build());

            return CarDTO.builder()
                    .name(saved.getName())
                    .brand(saved.getBrand())
                    .model(saved.getModel())
                    .date(saved.getDate())
                    .build();
        }
        return null;

    }

    public Car getOneCarById(Long id) {
        return carRepository.findById(id).get();


    }

    public HttpStatus deleteOne(Long id) {
        var findCar = carRepository.findById(id).get();
        if (Objects.nonNull(findCar)) {
            carRepository.delete(findCar);
            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;
    }

    public HttpStatus update(Car carDTO, String username) {
        var findCar = carRepository.findById(carDTO.getId()).get();
        var findUser = userRepository.findByUsername(username).get();
        if (Objects.nonNull(findCar)) {
            if (Objects.nonNull(findCar)) {
                ((Car) findCar).setBrand(carDTO.getBrand());
                ((Car) findCar).setName(carDTO.getName());
                ((Car) findCar).setModel(carDTO.getModel());
                ((Car) findCar).setDate(carDTO.getDate());
                carRepository.save((Car) findCar);

            }
            return HttpStatus.OK;
        }

        return HttpStatus.BAD_REQUEST;

    }
}
