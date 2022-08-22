package com.demo.loadmes.Service;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class DeviceMonitorServiceImpl implements DeviceMonitorService{

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    private static final String DeviceObjects="monitor";
    private static boolean scheduledMonitorStartOrNot=false;

    @Override
    public void startMonitorService() {
        if(!scheduledMonitorStartOrNot){
            scheduledMonitor();
            scheduledMonitorStartOrNot=true;
        }
    }

    @Override
    public void stopMonitorService() {
        if(scheduledMonitorStartOrNot){
            scheduledMonitorEnd();
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
            scheduledMonitorEnd();
            scheduledMonitorStartOrNot=false;
        }
        stringRedisTemplate.opsForSet().remove(DeviceObjects,deviceName);
    }

    @Override
    @Scheduled(cron = "* * * * * ?")
    @Async
    public void scheduledMonitor() {
        String time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        Set<String> monitorsSet=stringRedisTemplate.opsForSet().members(DeviceObjects);
        for(String str: Objects.requireNonNull(monitorsSet)){
            String power=stringRedisTemplate.opsForValue().get(str);
            stringRedisTemplate.opsForHash().put(DeviceMonitorUtils.monitorTimeKeyGenerator(str),time, Objects.requireNonNull(power));
            scheduledMonitorReduce(str);
        }
    }

    @Override
    @Async
    public void scheduledMonitorReduce(String deviceName) {
        if(Objects.requireNonNull(stringRedisTemplate.opsForHash().size(DeviceMonitorUtils.monitorTimeKeyGenerator(deviceName))).intValue()==60){
            List<Object> powerList=stringRedisTemplate.opsForHash().values(DeviceMonitorUtils.monitorTimeKeyGenerator(deviceName));
            stringRedisTemplate.delete(DeviceMonitorUtils.monitorTimeKeyGenerator(deviceName));
            double powerSum=0.0;
            for(Object obj:powerList){
                powerSum+=Double.parseDouble((String)obj);
            }
            powerSum/=60;
            if(LocalDateTime.now().getHour()==1){
                stringRedisTemplate.delete(DeviceMonitorUtils.monitorAvgKeyGenerator(deviceName));
            }
            String time=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh"));
            stringRedisTemplate.opsForHash().put(DeviceMonitorUtils.monitorAvgKeyGenerator(deviceName),time,powerSum);
        }
    }

    @Override
    @Async
    public void scheduledMonitorEnd() {
        throw new RuntimeException();
        /*
            开摆
         */
    }

    @Override
    public Map<LocalDateTime, Double> getDeviceMonitorData(String deviceName) {
        Map<LocalDateTime, Double> result=new HashMap<>();
        Map<Object,Object> deviceDataMap=new HashMap<>();
        deviceDataMap.putAll(stringRedisTemplate.opsForHash().entries(DeviceMonitorUtils.monitorAvgKeyGenerator(deviceName)));
        deviceDataMap.putAll(stringRedisTemplate.opsForHash().entries(DeviceMonitorUtils.monitorTimeKeyGenerator(deviceName)));
        for (Map.Entry<Object, Object> entry : deviceDataMap.entrySet()) {
            result.put(DeviceMonitorUtils.monitorTimeStrFormatter((String)entry.getKey()),Double.parseDouble((String)entry.getValue()));
        }
        return result;
    }
}
