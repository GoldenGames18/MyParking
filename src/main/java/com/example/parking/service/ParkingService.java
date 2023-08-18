package com.example.parking.service;

import com.example.parking.model.Car;
import com.example.parking.model.Parking;

import java.util.List;

public interface ParkingService {

    public abstract boolean createParking(Parking parking);
    public abstract boolean deleteParking(Long id);
    public abstract boolean updateParking(Long id, Parking parking);
    public abstract List<Parking> getParkings();
    public abstract Parking getParking(Long id);
    public abstract boolean parkingIsEmpty(Long id);
    public abstract boolean addCar(Long id, Car car);
    public abstract boolean removeCar(Long id, Car car);


}
