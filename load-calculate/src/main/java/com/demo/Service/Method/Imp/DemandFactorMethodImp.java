package com.demo.Service.Method.Imp;


import com.demo.Entity.Device.Device;
import com.demo.Entity.Device.DeviceData;
import com.demo.Entity.Device.DeviceDoc;
import com.demo.Entity.Device.DeviceGroup;
import com.demo.Entity.Response.CalculateResp;
import com.demo.Entity.Response.Result;
import com.demo.Service.Method.DemandFactorMethod;
import com.demo.Util.DeviceTypeMatch;
import com.demo.Util.ResultFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DemandFactorMethodImp implements DemandFactorMethod {
    @Resource
    private DeviceTypeMatch deviceTypeMatch;

    private static double simultaneousFactor;

    public CalculateResp accept(DeviceGroup devices) {
        List<DeviceDoc> docs = new ArrayList<>();
        if (devices.getUnit().equalsIgnoreCase("w")) {
            for (Device i : devices.getDevices()) {
                docs.add(ResultFormat.format(new DeviceDoc(deviceTypeMatch.matchForDemandFactor(i), i.getData())));
            }
        } else {
            for (Device i : devices.getDevices()) {
                docs.add(new DeviceDoc(deviceTypeMatch.matchForDemandFactor(i), i.getData()));
            }
        }
        Result result = deviceDocHandler(docs);
        return new CalculateResp(true, "需要系数法", "kw", result);
    }

    public Result deviceDocHandler(List<DeviceDoc> docs) {
        double pc = 0, qc = 0, weight = 0;
        for (DeviceDoc doc : docs) {
            weight = 0;
            for (DeviceData data : doc.getData()) {
                weight += data.getPower() * data.getCount();
            }
            pc += weight * doc.getParam().getFactor();
            qc += weight * doc.getParam().getFactor() * doc.getParam().getTan();
        }
        simultaneousFactorGenerator(docs.size());
        if (docs.size()>=2) {
            pc *= simultaneousFactor;
            qc *= simultaneousFactor;
        }
        return ResultFormat.format(pc, qc);
    }

    public void simultaneousFactorGenerator(int size) {
        simultaneousFactor = ResultFormat.format(0.85 + (Math.random() * 0.1));
    }
}
