package com.demo.loadser.RespEntity.DeviceStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceClassAndObjectBranch {
    private String deviceClass;
    private List<String> deviceObject;

    public DeviceClassAndObjectBranch(String deviceClass){
        this.deviceClass=deviceClass;
        this.deviceObject=new ArrayList<>();
    }
}
