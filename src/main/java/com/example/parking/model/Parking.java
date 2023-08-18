package com.example.parking.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Parking")
public class Parking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull
    @NotEmpty(message = "The name of the car park is empty")
    @NotBlank(message = "The name of the car park is Blank")
    @Column(name = "name")
    private String name;


    @Column(name ="espace")
    @NotNull
    private Integer espace;


    private Integer currentEspace;

    @OneToMany
    @Column(name = "cars")
    private List<Car> cars;

    public Parking(){
        this.cars = new ArrayList<>();
        this.currentEspace = 0;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    public Integer getEspace() {
        return espace;
    }

    public Long getId() {
        return id;
    }

    public Integer getCurrentEspace() {
        return currentEspace;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEspace(Integer espace) {
        this.espace = espace;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }
    public void setCurrentEspace(Integer currentEspace) {
        this.currentEspace = currentEspace;
    }


    public boolean containsCar(Car car){
        return this.cars.contains(car);
    }

    public void addCar(Car car){

        this.cars.add(car);
        this.setCurrentEspace(getCurrentEspace()+1);
    }

    public void removeCar(Car car){

        this.cars.remove(car);
        this.setCurrentEspace(getCurrentEspace()-1);
    }
}


