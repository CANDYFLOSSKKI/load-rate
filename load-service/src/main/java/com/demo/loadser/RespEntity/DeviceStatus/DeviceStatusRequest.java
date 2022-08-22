package com.demo.loadser.RespEntity.DeviceStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
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
