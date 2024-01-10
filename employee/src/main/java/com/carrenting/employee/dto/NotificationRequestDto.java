package com.carrenting.employee.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequestDto {
    private int customerId;
    private String messageText;

}
