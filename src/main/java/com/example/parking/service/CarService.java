package com.example.parking.service;

import com.example.parking.model.Car;
import com.example.parking.model.Parking;

import java.util.List;
import java.util.Optional;

public interface CarService {
    public abstract boolean createCar(Car car);
    public abstract boolean deleteCar(Long id);
    public abstract boolean updateCar(Long id, Car car);
    public abstract List<Car> getCars();
    public abstract Car getCar(Long id);
    public abstract boolean carIsEmpty(Long id);
}
