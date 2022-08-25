package com.demo.loadmes.Service.Impl;

import com.demo.loadmes.Service.DeviceMonitorService;
import com.demo.loadmes.UtilPack.DeviceMonitorRedisKeyGenerator;
import com.demo.loadmes.UtilPack.DeviceMonitorTimeStrFormatter;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DeviceMonitorServiceImpl implements DeviceMonitorService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String DeviceObjects="monitor";
    private static boolean scheduledMonitorStartOrNot=false;

    @Override
    public void startMonitorService() {
        if(!scheduledMonitorStartOrNot){
            scheduledMonitorChangePower();
            scheduledMonitor();
            scheduledMonitorStartOrNot=true;
        }
    }

    @Override
    public void stopMonitorService() {
        if(scheduledMonitorStartOrNot){
            scheduledMonitorStartOrNot=false;
        }
    }

    @Override
    public void startDeviceMonitor(String deviceName) {
        stringRedisTemplate.opsForSet().add(DeviceObjects,deviceName);
        if(!scheduledMonitorStartOrNot){
            scheduledMonitor();
            scheduledMonitorStartOrNot=true;
        }
    }

    @Override
    public void stopDeviceMonitor(String deviceName) {
        if(Objects.requireNonNull(stringRedisTemplate.opsForSet().size("monitor")).intValue()==1&&scheduledMonitorStartOrNot){
            scheduledMonitorStartOrNot=false;
        }
        stringRedisTemplate.opsForSet().remove(DeviceObjects,deviceName);
    }

    @Override
    @Scheduled(cron = "0 0/1 * * * ? ")
    @Async
    public void scheduledMonitor() {
        if(scheduledMonitorStartOrNot){
            LocalDateTime nowTime=LocalDateTime.now().withSecond(0);
            String time=nowTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            Set<String> monitorsSet=stringRedisTemplate.opsForSet().members(DeviceObjects);
            for(String str: Objects.requireNonNull(monitorsSet)){
                if(Boolean.TRUE.equals(stringRedisTemplate.hasKey(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(str)))){
                    if(DeviceMonitorTimeStrFormatter.monitorTimeStrFormatter(
                            (String) Objects.requireNonNull(stringRedisTemplate.opsForHash().randomEntry(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(str))).getKey()
                    ).getHour()!= nowTime.getHour()){
                        scheduledMonitorReduce(str);
                    }
                }
                stringRedisTemplate.opsForHash().put(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(str),time, Objects.requireNonNull(stringRedisTemplate.opsForValue().get(str)));
            }
        }
    }

    @Override
    @Scheduled(cron = "30/59 * * * * ? ")
    @Async
    public void scheduledMonitorChangePower(){
        if(scheduledMonitorStartOrNot){
            Set<String> monitorsSet=stringRedisTemplate.opsForSet().members(DeviceObjects);
            for(String str:Objects.requireNonNull(monitorsSet)){
                double power=0.0;
                switch(str){
                    case "device01"->{power=Math.random()*400;}
                    case "device02"->{power=Math.random()*450;}
                    case "device03"->{power=Math.random()*305;}
                    default->{power=Math.random()*100;}
                }
                stringRedisTemplate.opsForValue().set(str,new DecimalFormat(".##").format(power));
            }
        }
    }

    @Override
    public void scheduledMonitorReduce(String deviceName) {
        List<Object> powerList=stringRedisTemplate.opsForHash().values(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(deviceName));
        double powerSum=0.0;
        for(Object obj:powerList){
            powerSum+=Double.parseDouble((String)obj);
        }
        powerSum/=powerList.size();
        String time= DeviceMonitorTimeStrFormatter.monitorTimeStrFormatter(
                (String) Objects.requireNonNull(
                        stringRedisTemplate.opsForHash().randomKey(
                                DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(deviceName)))
        ).withSecond(0).withMinute(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        stringRedisTemplate.opsForHash().put(DeviceMonitorRedisKeyGenerator.monitorAvgKeyGenerator(deviceName),time,new DecimalFormat(".##").format(powerSum));
        stringRedisTemplate.delete(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(deviceName));
    }

    @Override
    public Map<LocalDateTime, Double> getDeviceMonitorData(String deviceName) {
        Map<LocalDateTime, Double> result=new HashMap<>();
        Map<Object,Object> deviceDataMap=new HashMap<>();
        deviceDataMap.putAll(stringRedisTemplate.opsForHash().entries(DeviceMonitorRedisKeyGenerator.monitorAvgKeyGenerator(deviceName)));
        deviceDataMap.putAll(stringRedisTemplate.opsForHash().entries(DeviceMonitorRedisKeyGenerator.monitorTimeKeyGenerator(deviceName)));
        for (Map.Entry<Object, Object> entry : deviceDataMap.entrySet()) {
            result.put(DeviceMonitorTimeStrFormatter.monitorTimeStrFormatter((String)entry.getKey()),Double.parseDouble((String)entry.getValue()));
        }
        return result;
    }
}
