package com.demo.loadser.OpenFeignClient;

import com.demo.loadser.RecvEntity.DeviceClass;
import com.demo.loadser.RecvEntity.DeviceClassDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "load-device",contextId = "class")
public interface DeviceClassServiceClient {

    @GetMapping("/class/get/all")
    public List<DeviceClass> getAllDeviceClass();

    @GetMapping("/class/get/{id}")
    public DeviceClass getDeviceClass(@PathVariable Integer id);

    @GetMapping("/class/del")
    public void delDeviceClass(@RequestParam Integer ID);

    @PostMapping("/class/add")
    public void addDeviceClass(@RequestBody DeviceClassDoc doc);

    @PostMapping("/class/update/doc")
    public void updateDeviceClassByDoc(@RequestBody DeviceClassDoc doc);
}
