package com.demo.Util;

import com.demo.Service.Data.FactorDataService;
import com.demo.Entity.Device.Device;
import com.demo.Entity.Factor.FactorBundle;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceTypeMatch {

    @Resource
    private FactorDataService service;

    public FactorBundle matchForDemandFactor(Device device) {
        return service.getFactorDataForMethod(device.getName(),"D");
    }

    public FactorBundle matchForUtilizeFactor(Device device) {
        return service.getFactorDataForMethod(device.getName(),"U");
    }
}
