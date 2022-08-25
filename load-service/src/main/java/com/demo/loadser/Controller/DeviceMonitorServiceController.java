package com.demo.loadser.Controller;

import com.demo.loadser.RespDeviceInfoEntity.DeviceClassAndObjectBranch;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusRequest;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusRequestStrPattern;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusResponse;
import com.demo.loadser.Service.DeviceInfoProcessService;
import com.demo.loadser.UtilPack.DeviceTimeFormatter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeviceMonitorServiceController {
    @Resource
    private DeviceInfoProcessService service;

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
