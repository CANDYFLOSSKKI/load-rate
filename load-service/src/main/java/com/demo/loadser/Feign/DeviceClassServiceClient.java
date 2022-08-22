package com.demo.loadser.Feign;

import com.demo.loadser.RespEntity.DeviceClass;
import com.demo.loadser.RespEntity.DeviceClassDoc;
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
