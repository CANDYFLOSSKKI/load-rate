package com.demo.loaddev.Message.MessageEntity;

public enum DeviceMonitorMessageEnum {
    ADD_DEVICE_MONITOR("add_device_monitor"),
    DEL_DEVICE_MONITOR("del_device_monitor");

    private final String handle;
    private DeviceMonitorMessageEnum(String handle){
        this.handle=handle;
    }
}
