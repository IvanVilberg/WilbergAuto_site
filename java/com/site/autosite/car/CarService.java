package com.site.autosite.car;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CarService {
    

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }
    
    public List<Car> getCars() {
        return  carRepository.findAll();
    }

    public Car save(Car car) {
        System.out.println(car);
        return carRepository.save(car);
    }

    public Car getCarById(@PathVariable Long id) {
        return carRepository.findById(id).get();
    }

    public ResponseEntity<Car> updateCarById(@PathVariable Long id, @RequestBody Car carInfo){
        Car car  = carRepository.findById(id)
            .orElseThrow();
        car.setBrand(carInfo.getBrand());
        car.setModel(carInfo.getModel());
        car.setCarBody(carInfo.getCarBody());
        car.setImage(carInfo.getImage());
        
        Car updatedCar = carRepository.save(car);
        return ResponseEntity.ok(updatedCar);
    }

    public ResponseEntity<Map<String, Boolean>> deleteCar(@PathVariable Long id){
        Car car = carRepository.findById(id)
            .orElseThrow();
        carRepository.delete(car);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
