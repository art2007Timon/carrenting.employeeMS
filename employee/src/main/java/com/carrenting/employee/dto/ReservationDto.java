package com.carrenting.employee.dto;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Reservierungen darzustellen, die vom Microservice "Reservation" abgerufen werden.
public class ReservationDto {
    private Long reservationID;
    private Data startDate;
    private Data endDate;
    int customerID;
    int carID;
}
