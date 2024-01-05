package com.carrenting.employee.service;

import com.carrenting.employee.dto.CarDto;
import com.carrenting.employee.dto.CustomerDto;
import com.carrenting.employee.dto.ReservationDto;
import com.carrenting.employee.feign.CarClient;
import com.carrenting.employee.feign.CustomerClient;
import com.carrenting.employee.feign.ReservationClient;
import com.carrenting.employee.ports.in.EmployeeManager;
import com.carrenting.employee.ports.out.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeManager {

    private final EmployeeRepository employeeRepository;
    private final CarClient carClient;
    private final CustomerClient customerClient;
    private final ReservationClient reservationClient;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           CarClient carClient,
                           CustomerClient customerClient,
                           ReservationClient reservationClient) {
        this.employeeRepository = employeeRepository;
        this.carClient = carClient;
        this.customerClient = customerClient;
        this.reservationClient = reservationClient;
    }

    //------------------------[FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount]--------------------------------------
    @Override
    public boolean login(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password).isPresent();
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
}
