package com.carrenting.employee.ports.in;
import com.carrenting.employee.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

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

}
