package com.demo.loadser.RespDeviceInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusRequest {
    private String name;
    private LocalDateTime fromTime;
    private LocalDateTime toTime;
}
