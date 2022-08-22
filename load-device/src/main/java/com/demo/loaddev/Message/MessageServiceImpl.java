package com.demo.loaddev.Message;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MessageServiceImpl implements MessageService{
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void addDeviceMonitor(String name) {
        rabbitTemplate.convertAndSend("monitor",new MessageDoc(name,MessageEnum.ADD_DEVICE_MONITOR));
    }

    @Override
    public void delDeviceMonitor(String name) {
        rabbitTemplate.convertAndSend("monitor",new MessageDoc(name,MessageEnum.DEL_DEVICE_MONITOR));
    }
}
