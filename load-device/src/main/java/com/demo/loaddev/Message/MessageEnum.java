package com.demo.loaddev.Message;

public enum MessageEnum {
    ADD_DEVICE_MONITOR("add_device_monitor"),
    DEL_DEVICE_MONITOR("del_device_monitor");

    private final String handle;
    private MessageEnum(String handle){
        this.handle=handle;
    }
}
