package com.demo.loaddev.Controller;

import com.demo.loaddev.TableEntity.DeviceClass;
import com.demo.loaddev.TableEntity.DeviceObject;
import com.demo.loaddev.Service.DeviceObjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeviceObjectGetController {
    @Resource
    private DeviceObjectService service;

    @GetMapping("/object/get/{id}")
    public DeviceObject getDeviceObject(@PathVariable Integer id){
        return service.getDeviceObject(id);
    }
    @GetMapping("/object/get")
    public List<DeviceObject> getDeviceObject(@RequestParam List<Integer> id){
        return service.getDeviceObject(id);
    }

    @GetMapping("/object/get/name")
    public DeviceObject getDeviceObject(@RequestParam String name){
        return service.getDeviceObjectByName(name);
    }

    @GetMapping("/object/get/class/{id}")
    public DeviceClass getDeviceObjectConnectedClass(@PathVariable Integer id){
        return service.getDeviceObjectConnectedClass(id);
    }

    @GetMapping("/object/get/all")
    public List<DeviceObject> getAllDeviceObject(){
        return service.getBaseMapper().selectList(null);
    }
}
