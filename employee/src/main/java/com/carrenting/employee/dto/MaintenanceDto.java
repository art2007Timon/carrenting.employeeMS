package com.carrenting.employee.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MaintenanceDto {
    private int maintenanceID;
    private int carID;
    private Date startDate;
    private Date endDate;
    private String status;
}
