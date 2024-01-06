package com.carrenting.employee.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class GpsDto {
    private Integer trackingId;
    private Integer carId;
    private Date timestamp;
    private String location;
}
