package com.demo.loaddev.Controller;

import com.demo.loaddev.TableEntity.*;
import com.demo.loaddev.TableEntity.DeviceObject;
import com.demo.loaddev.Service.DeviceObjectService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class DeviceObjectManageController {
    @Resource
    private DeviceObjectService service;

    @PostMapping("/object/add")
    public void addDeviceObject(@RequestBody DeviceObjectDoc doc){
        service.addDeviceObject(doc);
    }

    @GetMapping("/object/del")
    public void delDeviceObject(@RequestParam Integer ID){
        service.delDeviceObject(ID);
    }

    @PostMapping("/object/update")
    public void updateDeviceObject(@RequestBody DeviceObject doc){
        service.updateDeviceObject(doc);
    }

    @PostMapping("/object/update/doc")
    public void updateDeviceObjectByDoc(@RequestBody DeviceObjectDoc doc){
        service.updateDeviceObject(doc);
    }

}
