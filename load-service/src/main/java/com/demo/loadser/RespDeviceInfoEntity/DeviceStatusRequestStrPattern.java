package com.demo.loadser.RespDeviceInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusRequestStrPattern {
    private String name;
    private String fromtime;
    private String totime;
}
