package com.carrenting.employee.feign;


import com.carrenting.employee.dto.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "car-service", url = "http://car:8080")
public interface CarClient {

    //Alle Fahrzeuge anzeigen
    @GetMapping("/api/car")
    List<CarDto> getAllCars();

    //Fahrzeug einfügen
    @PostMapping("/api/car")
    CarDto addCar(CarDto car);

    //Fahrzeugparameter ändern
    @PutMapping("/api/car/{licensePlate}")
    CarDto updateCar(@PathVariable("licensePlate") String licensePlate, @RequestBody CarDto car);


    @GetMapping("/api/car/{licensePlate}")
    CarDto getCar(@PathVariable String licensePlate);

    @DeleteMapping("/api/car/{licensePlate}")
    void deleteCar(@PathVariable String licensePlate);

}
