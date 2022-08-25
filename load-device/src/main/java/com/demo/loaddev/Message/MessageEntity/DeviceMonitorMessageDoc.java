package com.demo.loaddev.Message.MessageEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceMonitorMessageDoc {
    private String deviceName;
    private DeviceMonitorMessageEnum handle;
}
