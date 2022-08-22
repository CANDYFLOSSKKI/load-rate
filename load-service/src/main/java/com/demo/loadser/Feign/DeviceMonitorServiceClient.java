package com.demo.loadser.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.Map;

@FeignClient(name = "load-message")
public interface DeviceMonitorServiceClient {

    @GetMapping("/monitor/get")
    public Map<LocalDateTime,Double> getMonitorData(@RequestParam String name);
}
