package com.carrenting.employee.adapters.in;

import com.carrenting.employee.dto.*;
import com.carrenting.employee.feign.ReportClient;
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
    public EmployeeController(EmployeeManager employeeManager, ReportClient reportClient) {
        this.employeeManager = employeeManager;
    }

    //http://localhost:8081/api/employee/login
    //JSON: {"email": "mayerp@example.com", "password": "password123" }
    @PostMapping("/login")
    public ResponseEntity<Employee> login(@RequestBody Map<String, String> credentials) {
        Optional<Employee> employee = employeeManager.login(credentials.get("email"), credentials.get("password"));
        return employee.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    //Ergibt mehr Sicherheit, enthaelt nur 2 benoetigen Variablen.
    @Getter
    @Setter
    private static class LoginRequest {
        private String email;
        private String password;
    }

    //======================================[Car]====================================================
    //Alle Fahrzeuge anzeigen
    //GET http://localhost:8081/api/employee/car
    @GetMapping("/car")
    public ResponseEntity<List<CarDto>> getAllCars() {
        List<CarDto> cars = employeeManager.getAllCars();
        return ResponseEntity.ok(cars);
    }

    //Fahrzeug einfuegen
    //POST http://localhost:8081/api/employee/car
    //JSON: {"licensePlate": "RRKHM777","mileage": 1400,"brand": "PEUGOT","model": "311"} or with carID -> "carID" : 1
    @PostMapping("/car")
    public ResponseEntity<CarDto> addCar(@RequestBody CarDto car) {
        CarDto addCar = employeeManager.addCar(car);
        return ResponseEntity.ok(addCar);
    }

    //Fahrzeugparameter aendern
    //PUT http://localhost:8081/api/employee/car/RRKHM777
    @PutMapping("/car/{licensePlate}")
    public ResponseEntity<CarDto> updateCar(@PathVariable String licensePlate, @RequestBody CarDto car) {
        CarDto updatedCar = employeeManager.updateCar(licensePlate, car);
        return ResponseEntity.ok(updatedCar);
    }

    //Fahrzeug nach Kennzeichen
    //GET: http://localhost:8081/api/employee/car/MK9
    @GetMapping("/car/{licensePlate}")
    public ResponseEntity<CarDto> getCar(@PathVariable String licensePlate) {
        CarDto car = employeeManager.getCar(licensePlate);
        return ResponseEntity.ok(car);
    }

    //DEELETE: http://localhost:8081/api/employee/car/XYZ789
    @DeleteMapping("/car/{licensePlate}")
    public ResponseEntity<Void> deleteCar(@PathVariable String licensePlate) {
        employeeManager.deleteCar(licensePlate);
        return ResponseEntity.ok().build();
    }


    //======================================[Customers]====================================================
    //Alle Kunden anzeigen
    //GET: http://localhost:8081/api/employee/customer
    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = employeeManager.getAllCustomers();
        return ResponseEntity.ok(customers);
    }

    //======================================[Reservations]====================================================
    //Alle Reservierungen anzeigen
    //GET: http://localhost:8081/api/employee/reservation
    @GetMapping("/reservation")
    public ResponseEntity<List<ReservationDto>> getAllReservations() {
        List<ReservationDto> reservations = employeeManager.getAllReservations();
        return ResponseEntity.ok(reservations);
    }

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

    //Reservierung fuer einen Fahrzeug nach carID ansehen
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
    // PUT: http://localhost:8081/api/employee/maintenance/update/12
    // Body: { "status": "Kontrolle" } //✓
    @PutMapping("/maintenance/update/{id}")
    public ResponseEntity<MaintenanceDto> updateMaintenance(@PathVariable int id, @RequestBody MaintenanceDto maintenance) {
        MaintenanceDto updatedMaintenance = employeeManager.updateMaintenance(id, maintenance);
        return ResponseEntity.ok(updatedMaintenance);
    }

    // Wartungsdetails nach ID ✓
    // GET: http://localhost:8081/api/employee/maintenance/12
    @GetMapping("/maintenance/{id}") //✓
    public ResponseEntity<MaintenanceDto> getMaintenanceById(@PathVariable int id) {
        Optional<MaintenanceDto> maintenance = employeeManager.getMaintenanceById(id);
        return maintenance.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    //Alle Fahrzeugen die in Wartung befinden ✓
    // GET: http://localhost:8081/api/employee/maintenance/all
    @GetMapping("/maintenance/all") //✓
    public ResponseEntity<List<MaintenanceDto>> getAllMaintenances() {
        List<MaintenanceDto> allMaintenances = employeeManager.getAllMaintenances();
        return ResponseEntity.ok(allMaintenances);
    }

    //Wartungen nach ID loeschen ✓
    // DELETE: http://localhost:8081/api/employee/maintenance/delete/13
    @DeleteMapping("/maintenance/delete/{id}") //✓
    public ResponseEntity<Void> deleteMaintenance(@PathVariable int id) {
        employeeManager.deleteMaintenance(id);
        return ResponseEntity.ok().build();
    }

    //======================================[GPS]====================================================
    //GET: http://localhost:8081/api/employees/gps/current
    @GetMapping("/gps/current") //✓
    public ResponseEntity<List<GpsDto>> getNewestGpsLocationsPerCar() {
        List<GpsDto> GpsLocationsPerCar = employeeManager.getNewestGpsLocationsPerCar();
        return ResponseEntity.ok(GpsLocationsPerCar);
    }



    //======================================[REPORT]====================================================

    //GET: http://localhost:8081/api/employee/exportData?reportType=Reservation
    //GET: http://localhost:8081/api/employee/exportData?reportType=Car
    //GET: http://localhost:8081/api/employee/exportData?reportType=Customer
    //GET: http://localhost:8081/api/employee/exportData?reportType=Maintenance
    @GetMapping("/exportData")
    public String exportReport(@RequestParam("reportType") String reportType) {
        return employeeManager.exportData(reportType);
    }



    //======================================[Notification]====================================================

    //POST: http://localhost:8081/api/employee/sendMessage
    //BODY:     { "customerId": 1, "messageText": "This is a message." }
    @PostMapping("/sendMessage")
    public ResponseEntity<Void> sendMessage(@RequestBody NotificationRequestDto notificationRequest) {
        employeeManager.sendMessage(notificationRequest);
        return ResponseEntity.ok().build();
    }

}
