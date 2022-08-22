package com.demo.loaddev.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceObjectDoc {
    private String name;
    private String version;
    private int classid;
    private double ratedpower;
}