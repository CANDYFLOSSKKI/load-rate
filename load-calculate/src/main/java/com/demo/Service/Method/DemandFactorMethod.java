package com.demo.Service.Method;

import com.demo.Entity.Device.DeviceDoc;
import com.demo.Entity.Device.DeviceGroup;
import com.demo.Entity.Response.CalculateResp;
import com.demo.Entity.Response.Result;

import java.util.List;

public interface DemandFactorMethod{
    CalculateResp accept(DeviceGroup devices);

    Result deviceDocHandler(List<DeviceDoc> docs);

    void simultaneousFactorGenerator(int size);

}
