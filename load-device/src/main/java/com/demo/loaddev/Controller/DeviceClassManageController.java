package com.demo.loaddev.Controller;

import com.demo.loaddev.TableEntity.DeviceClass;
import com.demo.loaddev.TableEntity.DeviceClassDoc;
import com.demo.loaddev.Service.DeviceClassService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class DeviceClassManageController {
    @Resource
    private DeviceClassService service;

    @PostMapping("/class/add")
    public void addDeviceClass(@RequestBody DeviceClassDoc doc){
        service.addDeviceClass(doc);
    }

    @GetMapping("/class/del")
    public void delDeviceClass(@RequestParam Integer ID){
        service.delDeviceClass(ID);
    }

    @PostMapping("/class/update")
    public void updateDeviceClass(@RequestBody DeviceClass doc){
        service.updateDeviceClass(doc);
    }

    @PostMapping("/class/update/doc")
    public void updateDeviceClassByDoc(@RequestBody DeviceClassDoc doc){
        service.updateDeviceClass(doc);
    }

}
