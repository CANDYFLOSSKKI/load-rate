package com.demo.loadser.RespEntity.DeviceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusRequestStrPattern {
    private String name;
    private String fromtime;
    private String totime;
}
