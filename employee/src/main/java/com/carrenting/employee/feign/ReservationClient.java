package com.carrenting.employee.feign;

import com.carrenting.employee.dto.ReservationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "reservation-service", url = "http://localhost:8083")
public interface ReservationClient {
    @GetMapping("/api/reservation")
    List<ReservationDto> getAllReservations();

    @PostMapping("/api/reservation")
    ReservationDto createReservation(ReservationDto reservation);

    @PutMapping("/api/reservation/{id}")
    ReservationDto updateReservation(@PathVariable("id") Long id, ReservationDto reservation);

    @DeleteMapping("/api/reservation/{id}")
    void deleteReservation(@PathVariable("id") Long id);
}
