package com.demo.loadser.Controller;


import com.demo.loadser.OpenFeignClient.DeviceObjectServiceClient;
import com.demo.loadser.RecvEntity.DeviceObject;
import com.demo.loadser.RecvEntity.DeviceObjectDoc;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeviceObjectServiceController {
    @Resource
    private DeviceObjectServiceClient client;

    @GetMapping("/object/get/all")
    public List<DeviceObject> getAllDeviceObject(){
        return client.getAllDeviceObject();
    }

    @GetMapping("/object/get")
    public DeviceObject getDeviceObject(@RequestParam String name){
        return client.getDeviceObject(name);
    }

    @GetMapping("/object/del")
    public void delDeviceObject(@RequestParam Integer ID){
        client.delDeviceObject(ID);
    }

    @PostMapping("/object/add")
    public void addDeviceObject(@RequestBody DeviceObjectDoc doc){
        client.addDeviceObject(doc);
    }

    @PostMapping("/object/update")
    public void updateDeviceObject(@RequestBody DeviceObjectDoc doc){
        client.updateDeviceObjectByDoc(doc);
    }
}
