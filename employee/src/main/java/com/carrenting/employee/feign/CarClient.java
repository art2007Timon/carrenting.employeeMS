package com.carrenting.employee.feign;


import com.carrenting.employee.dto.CarDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "car-service", url = "http://localhost:8080")
public interface CarClient {

    //Alle Fahrzeuge anzeigen
    @GetMapping("/api/cars")
    List<CarDto> getAllCars();
}
