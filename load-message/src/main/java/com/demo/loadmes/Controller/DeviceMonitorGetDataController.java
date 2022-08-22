package com.demo.loadmes.Controller;

import com.demo.loadmes.Service.DeviceMonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class DeviceMonitorGetDataController {
    @Resource
    private DeviceMonitorService service;

    @GetMapping("/monitor/get")
    public Map<LocalDateTime,Double> getMonitorData(@RequestParam String name){
        return service.getDeviceMonitorData(name);
    }
}
