package com.demo.Entity.Device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceGroup {
    private String unit;
    private String cross;
    private String mode;
    private List<Device> devices;
}
