package com.demo.loadser.RespEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceClass {
    private Integer ID;
    private String name;        //设备类型名
    private String version;     //设备型号
    private Integer mode;       //设备工作制
    private Double cycle;       //设备负载持续率(可null)
}
