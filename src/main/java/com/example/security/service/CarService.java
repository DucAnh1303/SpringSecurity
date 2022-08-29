package com.example.security.service;

import com.example.security.entities.Car;
import com.example.security.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public List<Car> getByAll() {
        return carRepository.getListCar();
    }
}
