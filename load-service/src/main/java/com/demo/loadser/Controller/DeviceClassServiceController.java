package com.demo.loadser.Controller;

import com.demo.loadser.Feign.DeviceClassServiceClient;
import com.demo.loadser.RespEntity.DeviceClass;
import com.demo.loadser.RespEntity.DeviceClassDoc;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeviceClassServiceController {
    @Resource
    private DeviceClassServiceClient client;

    @GetMapping("/class/get/all")
    public List<DeviceClass> getAllDeviceClass(){
        return client.getAllDeviceClass();
    }

    @GetMapping("/class/del")
    public void delDeviceClass(@RequestParam Integer ID){
        client.delDeviceClass(ID);
    }

    @PostMapping("/class/add")
    public void addDeviceClass(@RequestBody DeviceClassDoc doc){
        client.addDeviceClass(doc);
    }

    @PostMapping("/class/update")
    public void updateDeviceClass(@RequestBody DeviceClassDoc doc){
        client.updateDeviceClassByDoc(doc);
    }
}
