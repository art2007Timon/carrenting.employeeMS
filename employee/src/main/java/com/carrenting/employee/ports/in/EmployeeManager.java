package com.carrenting.employee.ports.in;
import com.carrenting.employee.dto.*;
import com.carrenting.employee.ports.data.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

public interface EmployeeManager {

    Optional<Employee> login(String email, String password);



    //======================================[Car]====================================================
    List<CarDto> getAllCars();
    CarDto addCar(CarDto car);
    CarDto updateCar(String licensePlate, CarDto car);
    CarDto getCar(@PathVariable String licensePlate);
    void deleteCar(@PathVariable String licensePlate);

    //======================================[Customers]====================================================
    List<CustomerDto> getAllCustomers();





    //======================================[Reservations]====================================================
    List<ReservationDto> getAllReservations();
    ReservationDto addReservation(ReservationDto reservation);
    void deleteReservation(Long reservationId);
    List<ReservationDto> getReservationsForVehicle(@RequestParam("carID") int carID);
    List<CarDto> getAvailableVehicle();




    //======================================[Maintenance]====================================================
    //Erstellung der Wartung, Fahrzeugzuweisung
    MaintenanceDto scheduleMaintenance(MaintenanceDto maintenance);

    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    MaintenanceDto updateMaintenance(int id, MaintenanceDto maintenance);

    // Wartungsdetails nach ID
    Optional<MaintenanceDto> getMaintenanceById(int maintenanceId);


    //Alle Fahrzeugen die in Wartung befinden
    List<MaintenanceDto> getAllMaintenances();

    //Wartungen nach ID loeschen
    void deleteMaintenance(int maintenanceId);



    //======================================[GPS]====================================================
    List<GpsDto> getNewestGpsLocationsPerCar();



    //======================================[REPORT]====================================================
    String exportData(String reportType);


    //======================================[Notificatio]====================================================
    void sendMessage(NotificationRequestDto notificationRequest);
}
