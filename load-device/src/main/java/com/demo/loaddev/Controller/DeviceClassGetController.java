package com.demo.loaddev.Controller;

import com.demo.loaddev.Entity.Table.DeviceClass;
import com.demo.loaddev.Service.DeviceClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeviceClassGetController {
    @Resource
    private DeviceClassService service;

    @GetMapping("/class/get/{id}")
    public DeviceClass getDeviceClass(@PathVariable Integer id){
        return service.getDeviceClass(id);
    }
    @GetMapping("/class/get")
    public List<DeviceClass> getDeviceClass(@RequestParam List<Integer> id){
        return service.getDeviceClass(id);
    }
    @GetMapping("/class/get/all")
    public List<DeviceClass> getAllDeviceClass(){
        return service.getBaseMapper().selectList(null);
    }

}
