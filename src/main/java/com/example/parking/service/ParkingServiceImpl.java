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
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingRepository parkingRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public boolean createParking(Parking parking) {
        Optional<Parking> value = parkingRepository.findByName(parking.getName());
        if (value.isPresent()){
            return false;
        }
        this.parkingRepository.save(parking);
        return true;
    }

    @Override
    public boolean deleteParking(Long id) {
        Optional<Parking> value = parkingRepository.findById(id);
        if (value.isEmpty())
            return false;

        this.parkingRepository.delete(value.get());
        return true;
    }

    @Override
    public boolean updateParking(Long id, Parking parking) {
        Optional<Parking> newParking = parkingRepository.findByName(parking.getName());
        Optional<Parking> oldParking = parkingRepository.findById(id);
        if (newParking.isPresent())
            return false;

        if (oldParking.isPresent()){
            Parking old = oldParking.get();
            if (old.getCurrentEspace() > parking.getEspace())
                return false;
            old.setId(id);
            old.setName(parking.getName());
            old.setEspace(parking.getEspace());
            parkingRepository.save(old);
            return true;
        }
        return false;
    }

    @Override
    public List<Parking> getParkings() {
        return parkingRepository.findAll();
    }

    @Override
    public Parking getParking(Long id) {
        return parkingRepository.findById(id).get();
    }

    @Override
    public boolean parkingIsEmpty(Long id) {
        return parkingRepository.findById(id).isEmpty();
    }


    @Override
    public boolean addCar(Long id, Car car) {
        Optional<Parking> parking = parkingRepository.findById(id);
        Optional<Car> currentCar = carRepository.findByPlate(car.getPlate());
        if (currentCar.isEmpty()){
            carRepository.save(car);
        }
        currentCar = carRepository.findByPlate(car.getPlate());
        if (parking.get().containsCar(currentCar.get())){
            return false;
        }
        Parking updateParking = parking.get();
        if (updateParking.getCurrentEspace()+1 > updateParking.getEspace()){
            return false;
        }
        updateParking.addCar(currentCar.get());
        parkingRepository.save(updateParking);
        return  true;


    }

    @Override
    public boolean removeCar(Long id, Car car) {
        Optional<Parking> parking = parkingRepository.findById(id);
        Optional<Car> currentCar = carRepository.findByPlate(car.getPlate());
        if (currentCar.isEmpty()){
            return false;
        }
        Parking updateParking = parking.get();
        updateParking.removeCar(currentCar.get());
        parkingRepository.save(updateParking);
        return true;
    }
}
