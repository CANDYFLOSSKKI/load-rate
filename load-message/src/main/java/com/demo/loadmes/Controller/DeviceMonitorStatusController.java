package com.demo.loadmes.Controller;


import com.demo.loadmes.Service.DeviceMonitorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DeviceMonitorStatusController {
    @Resource
    private DeviceMonitorService service;

    @GetMapping("/monitor/start")
    public void startMonitorService(){
        service.startMonitorService();
    }

    @GetMapping("/monitor/stop")
    public void stopMonitorService(){
        service.stopMonitorService();
    }

}
