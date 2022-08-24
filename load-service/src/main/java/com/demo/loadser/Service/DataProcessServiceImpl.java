package com.demo.loadser.Service;

import com.demo.loadser.Feign.DeviceClassServiceClient;
import com.demo.loadser.Feign.DeviceMonitorServiceClient;
import com.demo.loadser.Feign.DeviceObjectServiceClient;
import com.demo.loadser.RespEntity.DeviceClass;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceClassAndObjectBranch;
import com.demo.loadser.RespEntity.DeviceObject;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusRequest;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DataProcessServiceImpl implements DataProcessService{
    @Resource
    private DeviceClassServiceClient deviceClassServiceClient;
    @Resource
    private DeviceObjectServiceClient deviceObjectServiceClient;
    @Resource
    private DeviceMonitorServiceClient deviceMonitorServiceClient;

    private static final DecimalFormat DecimalFormatter=new DecimalFormat(".##");
    private static final String[] UnitArray=new String[]{"KW","W"};
    private static final ObjectMapper mapper=new ObjectMapper();

    private static final Comparator<LocalDateTime> LdtComparator= (o1, o2) -> {
        if      (o1.isAfter(o2)) { return 1;  }
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
        Map<LocalDateTime,Double> targetMap=new TreeMap<>(LdtComparator);
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
        resp.setUnit(UnitArray[0]);
        resp.setRatedPower(Double.parseDouble(DecimalFormatter.format(targetObj.getRatedpower())));
        LocalDateTime nowPowerKey=targetMap.keySet().stream().max(LdtComparator).get();
        resp.setNowPower(Double.parseDouble(DecimalFormatter.format(targetMap.get(nowPowerKey))));
        resp.setNowLoadRate(Double.parseDouble(DecimalFormatter.format(resp.getNowPower()/resp.getRatedPower())));

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
        resp.setAvgPower1h(Double.parseDouble(DecimalFormatter.format(avg1hPower/avg1hNum)));
        resp.setMaxPower1h(Double.parseDouble(DecimalFormatter.format(max1hPower)));
        resp.setAvgPower24h(Double.parseDouble(DecimalFormatter.format(avg24hPower/avg24hNum)));
        targetMap.replaceAll((l, v) -> Double.valueOf(DecimalFormatter.format(targetMap.get(l) / resp.getRatedPower())));
        resp.setLoadRateLine(targetMap);
        return resp;
    }
}
