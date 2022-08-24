package com.demo.Entity.Device;

import com.demo.Entity.Factor.FactorBundle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDoc {
    private FactorBundle param;
    private List<DeviceData> data;
}
