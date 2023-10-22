package com.example.car.config;

import com.example.car.dto.response.CarDTO;
import com.example.car.dto.response.UserResponse;
import com.example.car.model.entity.Car;
import com.example.car.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructConfig {

    MapStructConfig INSTANCE= Mappers.getMapper(MapStructConfig.class);

    CarDTO carToCarResponse(Car car);
    UserResponse userToUserResponse(User user);
//    UserResponse carToCarResponse(User user);

}
