package com.example.parking.repository;

import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkingRepository extends JpaRepository<Parking, Long> {
    Optional<Parking> findByName(String name);

    Optional<Parking> findByCars(Car car);
}
