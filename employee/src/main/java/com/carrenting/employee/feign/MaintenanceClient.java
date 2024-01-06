package com.carrenting.employee.feign;

import com.carrenting.employee.dto.MaintenanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "maintenance-service", url = "http://localhost:8084")
public interface MaintenanceClient {

                //[FUNC-WART-010 - Planung von Fahrzeugwartungen]

    //Erstellung der Wartung, Fahrzeugzuweisung
    @PostMapping("/api/maintenance")
    MaintenanceDto scheduleMaintenance(@RequestBody MaintenanceDto maintenance);


                //[FUNC-WART-020 - Verfolgung des Wartungsstatus]

    // Aktualisierung des Wartungsstatus _ Kann von einem Werkstaattsmitarbeiter angewendet werden
    @PutMapping("/api/maintenance/{id}")
    MaintenanceDto updateMaintenance(@PathVariable int id, @RequestBody MaintenanceDto maintenance);


    // Wartungsdetails nach ID
    @GetMapping("/api/maintenance/{id}")
    MaintenanceDto getMaintenanceById(@PathVariable int id);


    //Alle Fahrzeugen die in Wartung befinden
    @GetMapping("/api/maintenance")
    List<MaintenanceDto> getAllMaintenances();

    //Wartungen nach ID loeschen
    @DeleteMapping("/api/maintenance/{id}")
    void deleteMaintenance(@PathVariable("id") int id);






}
