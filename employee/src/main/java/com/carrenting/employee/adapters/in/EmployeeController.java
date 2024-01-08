package com.carrenting.employee.adapters.in;

import com.carrenting.employee.dto.*;
import com.carrenting.employee.ports.data.Employee;
import com.carrenting.employee.ports.in.EmployeeManager;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    private final EmployeeManager employeeManager;

    @Autowired
    public EmployeeController(EmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }



    //------------------------[FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount]--------------------------------------

    //http://localhost:8081/api/employee/login
    //JSON: {"email": "mayerp@example.com", "password": "password123" }
    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Map<String, String> credentials) {
        Optional<Employee> employee = employeeManager.login(credentials.get("email"), credentials.get("password"));
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    //Ergibt mehr Sicherheit, enthält nur 2 benoetigen Variablen.
    @Getter
    @Setter
    private static class LoginRequest {
        private String email;
        private String password;
    }





    //------------------------[FUNC-MITA-020 – Übersicht von Kunden, Autos und Reservierungen]--------------------------------------

    //Alle Fahrzeuge anzeigen
    //GET http://localhost:8081/api/employee/car
    @GetMapping("/car")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = employeeManager.getAllCars();
        return ResponseEntity.ok(cars);
    }

    //Alle Kunden anzeigen
    //GET: http://localhost:8081/api/employee/customer
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = employeeManager.getAllCustomers();
        return ResponseEntity.ok(customers);
    }


    //Alle Reservierungen anzeigen
    //GET: http://localhost:8081/api/employee/reservation
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = employeeManager.getAllReservations();
        return ResponseEntity.ok(reservations);
    }




    //------------------------[FUNC-MITA-030 – Reservierungen verwalten]--------------------------------------

    //Neue Reservierung
    //POST: http://localhost:8081/api/employee/reservation
    //JSON:  {"reservationID": 16, "startDate": "2024-01-01T10:00:00", "endDate": "2024-01-07T15:00:00", "customerID": 1, "carID": 4 }
    @PostMapping("/reservation")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservation) {
        ReservationDto addReservation = employeeManager.addReservation(reservation);
        return ResponseEntity.ok(addReservation);
    }

    //Reservierung nach ID Loeschen
    //DELETE: http://localhost:8081/api/employee/reservation/5
    @DeleteMapping("/reservation/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        employeeManager.deleteReservation(id);
        return ResponseEntity.ok().build();
    }

    //Reservierung fuer einen Fahrzeig nach CARID ansehen
    // GET: http://localhost:8081/api/employee/reservation/vehicle?carID=3
    @GetMapping("/reservation/vehicle")
    public ResponseEntity<List<ReservationDto>> getReservationsForVehicle(@RequestParam("carID") int carID) {
        List<ReservationDto> reservations = employeeManager.getReservationsForVehicle(carID);
        return ResponseEntity.ok(reservations);
    }









    //======================================[Maintenance]====================================================

    //Erstellung der Wartung, Fahrzeugzuweisung
    // POST: http://localhost:8081/api/employee/maintenance/schedule
    // Body: { "carID": 1, "startDate": "2023-01-01", "endDate": "2023-01-03" }
    @PostMapping("/maintenance/schedule") //✓
    public ResponseEntity<MaintenanceDto> scheduleMaintenance(@RequestBody MaintenanceDto maintenance) {
        MaintenanceDto scheduledMaintenance = employeeManager.scheduleMaintenance(maintenance);
        return ResponseEntity.ok(scheduledMaintenance);
    }

    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    // PUT: http://localhost:8081/api/employees/maintenance/update/1
    // Body: { "status": "Kontrolle" } //✓
    @PutMapping("/maintenance/update/{id}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(@PathVariable int id, @RequestBody MaintenanceDto maintenance) {
        MaintenanceDto updatedMaintenance = employeeManager.updateMaintenance(id, maintenance);
        return ResponseEntity.ok(updatedMaintenance);
    }

    // Wartungsdetails nach ID
    // GET: http://localhost:8081/api/employees/maintenance/1
    @GetMapping("/maintenance/{id}") //✓
    public ResponseEntity<MaintenanceDto> getMaintenanceById(@PathVariable int id) {
        Optional<MaintenanceDto> maintenance = employeeManager.getMaintenanceById(id);
        return maintenance.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Alle Fahrzeugen die in Wartung befinden
    // GET: http://localhost:8081/api/employees/maintenance/all
    @GetMapping("/maintenance/all") //✓
    public ResponseEntity<List<MaintenanceDto>> getAllMaintenances() {
        List<MaintenanceDto> allMaintenances = employeeManager.getAllMaintenances();
        return ResponseEntity.ok(allMaintenances);
    }

    //Wartungen nach ID loeschen
    // DELETE: http://localhost:8081/api/employees/maintenance/delete/1
    @DeleteMapping("/maintenance/delete/{id}") //✓
    public ResponseEntity<Void> deleteMaintenance(@PathVariable int id) {
        employeeManager.deleteMaintenance(id);
        return ResponseEntity.ok().build();
    }




    //======================================[GPS]====================================================
    //GET: http://localhost:8081/api/employees/gps/current
    @GetMapping("/gps/current") //✓
    public ResponseEntity<List<GpsDto>> getNewestGpsLocationsPerCar(){
        List<GpsDto> GpsLocationsPerCar = employeeManager.getNewestGpsLocationsPerCar();
        return ResponseEntity.ok(GpsLocationsPerCar);
    }



}
