package com.demo.loadser.Feign;

import com.demo.loadser.RespEntity.DeviceObject;
import com.demo.loadser.RespEntity.DeviceObjectDoc;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "load-device",contextId = "object")
public interface DeviceObjectServiceClient {

    @GetMapping("/object/get/all")
    public List<DeviceObject> getAllDeviceObject();

    @GetMapping("/object/get/name")
    public DeviceObject getDeviceObject(@RequestParam String name);

    @PostMapping("/object/add")
    public void addDeviceObject(@RequestBody DeviceObjectDoc doc);

    @GetMapping("/object/del")
    public void delDeviceObject(@RequestParam Integer ID);

    @PostMapping("/object/update/doc")
    public void updateDeviceObjectByDoc(@RequestBody DeviceObjectDoc doc);

}
