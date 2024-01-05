package com.carrenting.employee.ports.in;
import com.carrenting.employee.dto.CarDto;
import com.carrenting.employee.dto.CustomerDto;
import com.carrenting.employee.dto.ReservationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeManager {

    //FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount
    boolean login(String email, String password);

    //FUNC-MITA-020 – Übersicht von Kunden, Autos und Reservierungen
    List<CarDto> getAllCars();
    List<CustomerDto> getAllCustomers();
    List<ReservationDto> getAllReservations();

    //FUNC-MITA-030 – Reservierungen verwalten
    ReservationDto addReservation(ReservationDto reservation);
    void deleteReservation(Long reservationId);

    List<ReservationDto> getReservationsForVehicle(@RequestParam("carID") int carID);
}
