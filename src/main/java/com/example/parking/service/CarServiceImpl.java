package com.example.parking.service;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import com.example.parking.repository.CarRepository;
import com.example.parking.repository.ParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService{

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ParkingRepository parkingRepository;

    @Override
    public boolean createCar(Car car) {
        Optional<Car> value = carRepository.findByPlate(car.getPlate());
        if (value.isPresent()){
            return false;
        }else{
            this.carRepository.save(car);
            return true;
        }
    }

    @Override
    public boolean deleteCar(Long id) {
        Optional<Car> value = carRepository.findById(id);
        if (value.isEmpty())
            return false;
        Optional<Parking> dataParking = this.parkingRepository.findByCars(value.get());
        if (dataParking.isPresent()){
            Parking parking = dataParking.get();
            parking.removeCar(value.get());
            this.parkingRepository.save(parking);
        }
        this.carRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateCar(Long id, Car car) {
        Optional<Car> newCar = carRepository.findByPlate(car.getPlate());
        Optional<Car> oldCar = carRepository.findById(id);
        if (newCar.isPresent())
            return false;

        if (oldCar.isPresent()){
            Car old = oldCar.get();
            old.setId(id);
            old.setPlate(car.getPlate());
            carRepository.save(old);
            return true;
        }
        return false;

    }

    @Override
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    @Override
    public Car getCar(Long id) {
        return carRepository.findById(id).get();
    }

    @Override
    public boolean carIsEmpty(Long id) {
        return carRepository.findById(id).isEmpty();
    }
}
