package com.carrenting.employee.feign;

import com.carrenting.employee.dto.NotificationRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification-service", url = "http://notification:8085")
public interface NotificationClient {
    @PostMapping("/send")
    void sendMessage(@RequestBody NotificationRequestDto notificationRequest);
}
