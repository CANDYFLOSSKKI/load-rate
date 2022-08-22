package com.demo.loadmes.Service;

import com.demo.loadmes.Entity.MessageDoc;
import com.demo.loadmes.Entity.MessageEnum;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MessageConsumeService {
    @Resource
    private DeviceMonitorService service;

    @RabbitListener(queues="monitor")
    public void listenDeviceMonitorMessage(MessageDoc doc){
        if(doc.getHandle()==MessageEnum.ADD_DEVICE_MONITOR){
            service.startDeviceMonitor(doc.getDeviceName());
        }else{
            service.stopDeviceMonitor(doc.getDeviceName());
        }
    }
}
