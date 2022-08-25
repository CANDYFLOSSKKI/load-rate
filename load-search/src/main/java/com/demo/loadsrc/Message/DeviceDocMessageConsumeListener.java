package com.demo.loadsrc.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DeviceDocMessageConsumeListener {

    @RabbitListener(queues="monitor")
    public void listenDeviceMonitorMessage(){
    }
}