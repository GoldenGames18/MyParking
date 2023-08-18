package com.example.parking.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Car")
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "plate")
    @NotNull
    @NotEmpty(message = "Plate is Empty")
    @NotBlank(message = "Plate is Blank")
    //@Pattern(regexp = "^[0-9]-[A-Z]{3}-[0-9]{3}$", message = "Invalid plate")
    private String plate;


    public Car(String plate){
        this.plate = plate;
    }

    public Car() {}

    public String getPlate() {
        return plate;
    }


    public void setPlate(String plate) {
        this.plate = plate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (!id.equals(car.id)) return false;
        return plate.equals(car.plate);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + plate.hashCode();
        return result;
    }
}
