package com.demo.loadmes.Message;

import com.demo.loadmes.Message.MessageEntity.DeviceMonitorMessageDoc;
import com.demo.loadmes.Message.MessageEntity.DeviceMonitorMessageEnum;
import com.demo.loadmes.Service.DeviceMonitorService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DeviceMonitorMessageConsumeListener {
    @Resource
    private DeviceMonitorService service;

    @RabbitListener(queues="monitor")
    public void listenDeviceMonitorMessage(DeviceMonitorMessageDoc doc){
        if(doc.getHandle()== DeviceMonitorMessageEnum.ADD_DEVICE_MONITOR){
            service.startDeviceMonitor(doc.getDeviceName());
        }else{
            service.stopDeviceMonitor(doc.getDeviceName());
        }
    }
}
