package com.demo.loadser.Controller;

import com.demo.loadser.RespEntity.*;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusRequest;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusRequestStrPattern;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusResponse;
import com.demo.loadser.Service.DataProcessService;
import com.demo.loadser.Service.DeviceTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DeviceMonitorServiceController {
    @Resource
    private DataProcessService service;

    @GetMapping("/device/get")
    public List<DeviceClassAndObjectBranch> getDeviceBranchList(){
        return service.getDeviceBranchList();
    }

    @PostMapping("/device/status")
    public DeviceStatusResponse getDeviceStatus(@RequestBody DeviceStatusRequestStrPattern req){
        return service.getDeviceStatus(new DeviceStatusRequest(req.getName(),
                DeviceTimeFormatter.format(req.getFromtime()),
                DeviceTimeFormatter.format(req.getTotime())
        ));
    }

}
