package com.demo.loaddev.Message.MessageService.Impl;

import com.demo.loaddev.Message.MessageEntity.DeviceMonitorMessageEnum;
import com.demo.loaddev.Message.MessageEntity.DeviceMonitorMessageDoc;
import com.demo.loaddev.Message.MessageService.DeviceMonitorMessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DeviceMonitorMessageServiceImpl implements DeviceMonitorMessageService {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void addDeviceMonitor(String name) {
        rabbitTemplate.convertAndSend("monitor",new DeviceMonitorMessageDoc(name, DeviceMonitorMessageEnum.ADD_DEVICE_MONITOR));
    }

    @Override
    public void delDeviceMonitor(String name) {
        rabbitTemplate.convertAndSend("monitor",new DeviceMonitorMessageDoc(name, DeviceMonitorMessageEnum.DEL_DEVICE_MONITOR));
    }
}
