package com.demo.Service.Method.Imp;

import com.demo.Entity.Device.*;
import com.demo.Entity.Response.CalculateResp;
import com.demo.Entity.Response.Result;
import com.demo.Service.Method.UtilizeFactorMethod;
import com.demo.Util.DeviceTypeMatch;
import com.demo.Util.KmtFormMatch;
import com.demo.Util.ResultFormat;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UtilizeFactorMethodImp implements UtilizeFactorMethod {
    @Resource
    private DeviceTypeMatch deviceTypeMatch;
    @Resource
    private KmtFormMatch kmtFormMatch;
    private static boolean continuous;
    private static int crossSection;
    private static final double Kl_NeqLessThanThree_cont = 1;
    private static final double Kl_NeqMoreThanThree_cont = 0.9;
    private static final double Kl_NeqLessThanThree_time=1.15;
    private static final double Kl_NeqMoreThanThree_time=1;
    private static final double i=1;

    public CalculateResp accept(DeviceGroup devices) {
        continuous= devices.getMode().equalsIgnoreCase("continuous");
        if(devices.getMode()==null){
            continuous=true;
        }
        crossSection = -1;
        List<DeviceDoc> docs = new ArrayList<>();
        if (devices.getUnit().equalsIgnoreCase("w")) {
            for (Device i : devices.getDevices()) {
                docs.add(ResultFormat.format(new DeviceDoc(deviceTypeMatch.matchForUtilizeFactor(i), i.getData())));
            }
        } else {
            for (Device i : devices.getDevices()) {
                docs.add(new DeviceDoc(deviceTypeMatch.matchForUtilizeFactor(i), i.getData()));
            }
        }
        if (devices.getCross().equalsIgnoreCase("small")) {
            crossSection = 0;
        } else if (devices.getCross().equalsIgnoreCase("medium")) {
            crossSection = 1;
        } else if (devices.getCross().equalsIgnoreCase("large")) {
            crossSection = 2;
        }
        return new CalculateResp(true, "利用系数法", "kw", deviceDocHandler(docs));
    }

    public Result deviceDocHandler(List<DeviceDoc> docs) {
        DeviceWeight docWeight = new DeviceWeight();
        for (DeviceDoc doc : docs) {
            docWeight.addAnotherWight(weightGenerator(doc));
        }
        if (crossSection == -1) {
            if (docWeight.getSumP() >= 450) {
                crossSection = 2;
            } else if (docWeight.getSumP() <= 105) {
                crossSection = 0;
            } else {
                crossSection = 1;
            }
        }
        double Neq = Math.pow(docWeight.getSumP(), 2) / docWeight.getSumPDouble();
        if(Neq<=5){
            double kl=0;
            if(Neq<=3){
                if(continuous){kl=Kl_NeqLessThanThree_cont;}
                else{kl=Kl_NeqLessThanThree_time;}
            }else{
                if(continuous){kl=Kl_NeqMoreThanThree_cont;}
                else{kl=Kl_NeqMoreThanThree_time;}
            }
            return ResultFormat.format(docWeight.getSumP()*kl,docWeight.getSumQ()*kl);
        }
        double Kut = docWeight.getAvgP() / docWeight.getSumP();
        double Km = kmtFormMatch.match(Neq, Kut, crossSection);
        return ResultFormat.format(Km * docWeight.getAvgP(), Km * docWeight.getAvgQ());
    }

    public DeviceWeight weightGenerator(DeviceDoc doc) {
        DeviceWeight weight = new DeviceWeight();
        for (DeviceData data : doc.getData()) {
            double pe = data.getPower() * data.getCount();
            weight.addAvgP(pe * doc.getParam().getFactor());
            weight.addAvgQ(pe * doc.getParam().getFactor() * doc.getParam().getTan());
            weight.addSumP(pe);
            weight.addSumQ(pe * doc.getParam().getTan());
            weight.addSumPDouble(Math.pow(data.getPower(), 2) * data.getCount());
        }
        return weight;
    }
}
