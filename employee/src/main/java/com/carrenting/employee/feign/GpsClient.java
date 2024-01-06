package com.carrenting.employee.feign;


import com.carrenting.employee.dto.GpsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "gps-service", url = "http://localhost:8090")
public interface GpsClient {
    @GetMapping("/api/gps")
    List<GpsDto> getNewestGpsLocationsPerCar();
}
