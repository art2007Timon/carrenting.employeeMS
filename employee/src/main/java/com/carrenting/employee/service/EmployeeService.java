package com.carrenting.employee.service;

import com.carrenting.employee.dto.*;
import com.carrenting.employee.feign.*;
import com.carrenting.employee.ports.data.Employee;
import com.carrenting.employee.ports.in.EmployeeManager;
import com.carrenting.employee.ports.out.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements EmployeeManager {

    private final EmployeeRepository employeeRepository;
    private final CarClient carClient;
    private final CustomerClient customerClient;
    private final ReservationClient reservationClient;
    private final MaintenanceClient maintenanceClient;
    private final GpsClient gpsClient;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           CarClient carClient,
                           CustomerClient customerClient,
                           ReservationClient reservationClient,
                           MaintenanceClient maintenanceClient,
                           GpsClient gpsClient) {
        this.employeeRepository = employeeRepository;
        this.carClient = carClient;
        this.customerClient = customerClient;
        this.reservationClient = reservationClient;
        this.maintenanceClient = maintenanceClient;
        this.gpsClient = gpsClient;
    }

    //------------------------[FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount]--------------------------------------
    @Override
    public Optional<Employee> login(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password);
    }


    //------------------------[FUNC-MITA-020 – Übersicht von Kunden, Autos und Reservierungen]--------------------------------------
    @Override
    public List<CarDto> getAllCars() {
        return carClient.getAllCars();
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        return customerClient.getAllCustomers();
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        return reservationClient.getAllReservations();
    }


    //------------------------[FUNC-MITA-030 – Reservierungen verwalten]--------------------------------------
    @Override
    public ReservationDto addReservation(ReservationDto reservation) {
        return reservationClient.addReservation(reservation);
    }

    @Override
    public void deleteReservation(Long reservationId) {
        reservationClient.deleteReservation(reservationId);
    }

    public List<ReservationDto> getReservationsForVehicle(int carID) {
        return reservationClient.getReservationsForVehicle(carID);
    }





    //======================================[Maintenance]====================================================

    @Override
    public MaintenanceDto scheduleMaintenance(MaintenanceDto maintenance) {
        return maintenanceClient.scheduleMaintenance(maintenance);
    }


    @Override
    public MaintenanceDto updateMaintenance(int id, MaintenanceDto maintenance) {
        return maintenanceClient.updateMaintenance(id, maintenance);
    }

    @Override
    public Optional<MaintenanceDto> getMaintenanceById(int maintenanceId) {
        return Optional.ofNullable(maintenanceClient.getMaintenanceById(maintenanceId));
    }


    @Override
    public List<MaintenanceDto> getAllMaintenances() {
        return maintenanceClient.getAllMaintenances();
    }

    @Override
    public void deleteMaintenance(int maintenanceId) {
        maintenanceClient.deleteMaintenance(maintenanceId);
    }






    //======================================[GPS]====================================================
    @Override
    public List<GpsDto> getNewestGpsLocationsPerCar() {
        return gpsClient.getNewestGpsLocationsPerCar();
    }


}
