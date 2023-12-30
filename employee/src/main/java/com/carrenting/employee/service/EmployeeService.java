package com.carrenting.employee.service;

import com.carrenting.employee.dto.CarDto;
import com.carrenting.employee.dto.CustomerDto;
import com.carrenting.employee.dto.ReservationDto;
import com.carrenting.employee.ports.in.EmployeeManager;
import com.carrenting.employee.ports.out.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class EmployeeService implements EmployeeManager {

    private final EmployeeRepository employeeRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, RestTemplate restTemplate) {
        this.employeeRepository = employeeRepository;
        this.restTemplate = restTemplate;
    }



    //------------------------[FUNC-MITA-010 – Anmeldung in einen Mitarbeiteraccount]--------------------------------------
    @Override
    public boolean login(String email, String password) {
        return employeeRepository.findByEmailAndPassword(email, password).isPresent();
    }



    //------------------------[FUNC-MITA-020 – Übersicht von Kunden, Autos und Reservierungen]--------------------------------------
    @Override
    public List<CarDto> getAllCars() {
        CarDto[] cars = restTemplate.getForObject("http://localhost:8080/api/cars", CarDto[].class);
        return Arrays.asList(cars);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        CustomerDto[] customers = restTemplate.getForObject("http://localhost:8082/api/customers", CustomerDto[].class);
        return Arrays.asList(customers);
    }

    @Override
    public List<ReservationDto> getAllReservations() {
        ReservationDto[] reservations = restTemplate.getForObject("http://localhost:8083/api/reservation", ReservationDto[].class);
        return Arrays.asList(reservations);
    }


    //------------------------[FUNC-MITA-030 – Reservierungen verwalten]--------------------------------------
    @Override
    public ReservationDto createReservation(ReservationDto reservation) {
        return restTemplate.postForObject("http://localhost:8083/api/reservation", reservation, ReservationDto.class);
    }

    @Override
    public ReservationDto updateReservation(Long reservationId, ReservationDto reservation) {
        restTemplate.put("http://localhost:8083/api/reservation/" + reservationId, reservation);
        return reservation;
    }


    @Override
    public void deleteReservation(Long reservationId) {
        restTemplate.delete("http://localhost:8083/api/reservation/" + reservationId);
    }
}
