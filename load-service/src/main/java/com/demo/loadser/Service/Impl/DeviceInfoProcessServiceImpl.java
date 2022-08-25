package com.demo.loadser.Service.Impl;

import com.demo.loadser.OpenFeignClient.DeviceClassServiceClient;
import com.demo.loadser.OpenFeignClient.DeviceMonitorServiceClient;
import com.demo.loadser.OpenFeignClient.DeviceObjectServiceClient;
import com.demo.loadser.RecvEntity.DeviceClass;
import com.demo.loadser.RespDeviceInfoEntity.DeviceClassAndObjectBranch;
import com.demo.loadser.RecvEntity.DeviceObject;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusRequest;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusResponse;
import com.demo.loadser.Service.DeviceInfoProcessService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DeviceInfoProcessServiceImpl implements DeviceInfoProcessService {
    @Resource
    private DeviceClassServiceClient deviceClassServiceClient;
    @Resource
    private DeviceObjectServiceClient deviceObjectServiceClient;
    @Resource
    private DeviceMonitorServiceClient deviceMonitorServiceClient;

    private static final DecimalFormat DECIMAL_FORMATTER =new DecimalFormat(".##");
    private static final String[] UNIT_ARRAY =new String[]{"KW","W"};
    private static final Comparator<LocalDateTime> LDT_COMPARATOR = (o1, o2) -> {
        if (o1.isAfter(o2)) { return 1; }
        else if (o1.isBefore(o2)){ return -1; }
        return 0;
    };

    @Override
    public List<DeviceClassAndObjectBranch> getDeviceBranchList() {
        List<DeviceClassAndObjectBranch> result=new ArrayList<>();
        List<DeviceClass> classList=deviceClassServiceClient.getAllDeviceClass();
        List<DeviceObject> objectList=deviceObjectServiceClient.getAllDeviceObject();
        for(DeviceClass dc:classList){
            DeviceClassAndObjectBranch branch=new DeviceClassAndObjectBranch(dc.getName());
            for(DeviceObject obj:objectList){
                if(Objects.equals(obj.getClassid(),dc.getID())){
                    branch.getDeviceObject().add(obj.getName());
                }
            }
            result.add(branch);
        }
        return result;
    }

    @Override
    public DeviceStatusResponse getDeviceStatus(DeviceStatusRequest req) {
        DeviceStatusResponse resp=new DeviceStatusResponse();
        DeviceObject targetObj=deviceObjectServiceClient.getDeviceObject(req.getName());
        Map<LocalDateTime,Double> dataMap=deviceMonitorServiceClient.getMonitorData(targetObj.getName());
        Map<LocalDateTime,Double> targetMap=new TreeMap<>(LDT_COMPARATOR);
        for(LocalDateTime ldt:dataMap.keySet()){
            if(!(ldt.isBefore(req.getFromTime())||ldt.isAfter(req.getToTime()))){
                targetMap.put(ldt,dataMap.get(ldt));
            }
        }
        if(targetMap.isEmpty()){
            return null;
        }
        resp.setName(req.getName());
        resp.setVersion(targetObj.getVersion());
        resp.setUnit(UNIT_ARRAY[0]);
        resp.setRatedPower(Double.parseDouble(DECIMAL_FORMATTER.format(targetObj.getRatedpower())));
        Optional<LocalDateTime> optLdt=targetMap.keySet().stream().max(LDT_COMPARATOR);
        LocalDateTime nowPowerKey;
        if(optLdt.isPresent()){
            nowPowerKey=optLdt.get();
        }else{ return null; }
        resp.setNowPower(Double.parseDouble(DECIMAL_FORMATTER.format(targetMap.get(nowPowerKey))));
        resp.setNowLoadRate(Double.parseDouble(DECIMAL_FORMATTER.format(resp.getNowPower()/resp.getRatedPower())));

        double max1hPower=0.0,avg1hPower=0.0,avg24hPower=0.0;
        int avg1hNum=0,avg24hNum=0;
        for(Map.Entry<LocalDateTime,Double> entry:targetMap.entrySet()){
            ++avg24hNum;
            avg24hPower+=entry.getValue();
            if(Duration.between(nowPowerKey, entry.getKey()).compareTo(Duration.ofHours(1))<0){
                max1hPower=max1hPower>entry.getValue()?max1hPower:entry.getValue();
                ++avg1hNum;
                avg1hPower+=entry.getValue();
            }
        }
        resp.setAvgPower1h(Double.parseDouble(DECIMAL_FORMATTER.format(avg1hPower/avg1hNum)));
        resp.setMaxPower1h(Double.parseDouble(DECIMAL_FORMATTER.format(max1hPower)));
        resp.setAvgPower24h(Double.parseDouble(DECIMAL_FORMATTER.format(avg24hPower/avg24hNum)));
        targetMap.replaceAll((l, v)->Double.valueOf(DECIMAL_FORMATTER.format(targetMap.get(l)/resp.getRatedPower())));
        resp.setLoadRateLine(targetMap);
        return resp;
    }
}
