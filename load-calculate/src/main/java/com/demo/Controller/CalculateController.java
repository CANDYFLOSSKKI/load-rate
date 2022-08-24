package com.demo.Controller;

import com.demo.Entity.Device.DeviceGroup;
import com.demo.Entity.Response.CalculateResp;
import com.demo.Service.Method.DemandFactorMethod;
import com.demo.Service.Method.UtilizeFactorMethod;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cal")
public class CalculateController {
    @Resource
    private DemandFactorMethod demandFactorMethod;
    @Resource
    private UtilizeFactorMethod utilizeFactorMethod;


    @PostMapping("/auto")
    public CalculateResp calculateAuto(@RequestBody DeviceGroup deviceGroup) {
        if (deviceGroup.getDevices().size()>=5) {
            return demandFactorMethod.accept(deviceGroup);
        }
        return utilizeFactorMethod.accept(deviceGroup);
    }

    @PostMapping("/{type}")
    public CalculateResp calculateByType(@RequestBody DeviceGroup deviceGroup, @PathVariable String type){
        if(type.equalsIgnoreCase("D")){
            return demandFactorMethod.accept(deviceGroup);
        }
        return utilizeFactorMethod.accept(deviceGroup);
    }

}
