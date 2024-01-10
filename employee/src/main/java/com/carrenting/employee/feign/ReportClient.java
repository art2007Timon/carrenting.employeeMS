package com.carrenting.employee.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "report-service", url = "http://localhost:8088")
public interface ReportClient {
    @GetMapping("/api/export/csv")
    String exportAsCsv(@RequestParam("requestType") String requestType);
}