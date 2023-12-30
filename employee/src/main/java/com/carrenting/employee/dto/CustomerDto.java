package com.carrenting.employee.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Kunden darzustellen, die vom Microservice "Customer" abgerufen werden.
public class CustomerDto {
    private Integer customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
