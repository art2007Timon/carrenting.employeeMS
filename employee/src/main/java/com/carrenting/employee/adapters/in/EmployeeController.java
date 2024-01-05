package com.carrenting.employee.adapters.in;

import com.carrenting.employee.dto.CarDto;
import com.carrenting.employee.dto.CustomerDto;
import com.carrenting.employee.dto.ReservationDto;
import com.carrenting.employee.ports.in.EmployeeManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeManager employeeManager;

    @Autowired
    public EmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }



    //------------------------[FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount]--------------------------------------

    //http://localhost:8081/api/employees/login
    //JSON: {"email": "mayerp@example.com", "password": "password123" }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isAuthenticated = employeeManager.login(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Unauthorized");
        }
    }

    //Ergibt mehr Sicherheit, enthält nur 2 benoetigen Variablen.
    @Getter
    @Setter
    private static class LoginRequest {
        private String email;
        private String password;
    }





    //------------------------[FUNC-MITA-020 – Übersicht von Kunden, Autos und Reservierungen]--------------------------------------

    //GET http://localhost:8081/api/employees/cars
    @GetMapping("/cars")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = employeeManager.getAllCars();
        return ResponseEntity.ok(cars);
    }
    //GET: http://localhost:8081/api/employees/customers
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = employeeManager.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //GET: http://localhost:8081/api/employees/reservation
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = employeeManager.getAllReservations();
        return ResponseEntity.ok(reservations);
    }




    //------------------------[FUNC-MITA-030 – Reservierungen verwalten]--------------------------------------

    //POST: http://localhost:8081/api/employees/reservation
    //    //JSON: {"customerId": 1, "carId": 1, "startDate": "2023-07-01T10:00:00", "endDate": "2023-07-03T15:00:00" }
    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> createReservation(@RequestBody ReservationDto reservation) {
        ReservationDto createdReservation = employeeManager.createReservation(reservation);
        return ResponseEntity.ok(createdReservation);
    }

    @PutMapping("/reservation/{id}")
    public ResponseEntity<ReservationDto> updateReservation(@PathVariable Long id, @RequestBody ReservationDto reservation) {
        ReservationDto updatedReservation = employeeManager.updateReservation(id, reservation);
        return ResponseEntity.ok(updatedReservation);
    }

    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        employeeManager.deleteReservation(id);
        return ResponseEntity.ok().build();
    }
}
