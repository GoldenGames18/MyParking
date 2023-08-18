package com.example.parking.controller;
import com.example.parking.model.Car;
import com.example.parking.model.Parking;
import com.example.parking.repository.ParkingRepository;
import com.example.parking.service.ParkingService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;

    @GetMapping(path = "/all")
    public List<Parking> getParkings(){
        return parkingService.getParkings();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Parking> getParking(@PathVariable(value = "id") Long id){
        try{
            return ResponseEntity.ok(parkingService.getParking(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping(path = "")
    public ResponseEntity<String> addParking(@Valid @RequestBody Parking parking){
        if (!parkingService.createParking(parking)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This parking already exists");
        }else{
            return ResponseEntity.ok("Parking registered successfully");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> removeParking(@PathVariable @NotNull Long id){
        if (!parkingService.deleteParking(id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This parking does not exist");
        else
            return ResponseEntity.ok("Parking deleted successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editParking(@PathVariable @NotNull Long id, @Valid @RequestBody Parking newParking){
        if (parkingService.parkingIsEmpty(id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This parking does not exist");
        if (!parkingService.updateParking(id, newParking))
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        else
            return ResponseEntity.ok("The parking has been modified.");
    }

    @PostMapping(path = "/{id}/add")
    public ResponseEntity<String> addCarInParking(@PathVariable Long id, @Valid @RequestBody Car car) {
        if(!parkingService.addCar(id,car)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }else{
            return ResponseEntity.ok().body("Car car has been added");
        }

    }

    @DeleteMapping(path = "/{id}/remove")
    public ResponseEntity<String> removeCarInParking(@PathVariable Long id, @Valid @RequestBody Car car) {
        if(!parkingService.removeCar(id,car)){
            return ResponseEntity.badRequest().body("The car is not in this parking");
        }else{
            return ResponseEntity.ok().body("Car car has been remove");
        }
    }


}
