package com.demo.loadser.RespDeviceInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceStatusResponse {
    private String name;
    private String version;
    private String unit;
    private Double ratedPower;
    private Double nowPower;
    private Double nowLoadRate;
    private Double avgPower1h;
    private Double maxPower1h;
    private Double avgPower24h;
    private Map<LocalDateTime,Double> loadRateLine;
}
