package com.demo.loaddev.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceClassDoc {
    private String name;
    private String version;
    private int mode;
    private double cycle;
}
