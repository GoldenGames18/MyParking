package com.example.parking.controller;
import com.example.parking.model.Car;
import com.example.parking.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;



@RestController
@RequestMapping(path = "/car")
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(path ="/all")
    public ResponseEntity<List<Car>> getCars(){
        return ResponseEntity.ok(carService.getCars());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Car> getCar(@PathVariable (value = "id") @NotNull Long id){
        try{
            return ResponseEntity.ok(carService.getCar(id));
        }catch (NoSuchElementException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(path = "")
    public ResponseEntity<String> addCar(@Valid @RequestBody Car car){
        if (!carService.createCar(car)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This car already exists");
        }else{
            return ResponseEntity.ok("Car registered successfully");
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> removeCar(@PathVariable @NotNull Long id){
        if (!carService.deleteCar(id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This car does not exist");
        else
            return ResponseEntity.ok("Car deleted successfully");
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<String> editCar(@PathVariable @NotNull Long id, @Valid @RequestBody Car newPlate){;
        if (carService.carIsEmpty(id))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This car does not exist");
        if (!carService.updateCar(id, newPlate))
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This car already exists");
        else
            return ResponseEntity.ok("The car has been modified.");
    }

}
