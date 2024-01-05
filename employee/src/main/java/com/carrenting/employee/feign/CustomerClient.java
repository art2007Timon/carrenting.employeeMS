package com.carrenting.employee.feign;

import com.carrenting.employee.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "customer-service", url = "http://localhost:8082")
public interface CustomerClient {

    //Alle Kunden anzeigen
    @GetMapping("/api/customer")
    List<CustomerDto> getAllCustomers();
}