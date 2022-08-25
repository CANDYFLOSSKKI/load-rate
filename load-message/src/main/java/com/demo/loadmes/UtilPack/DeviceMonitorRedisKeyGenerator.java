package com.demo.loadmes.UtilPack;

public class DeviceMonitorRedisKeyGenerator {
    public static String monitorTimeKeyGenerator(String deviceName){
        return deviceName + "_time";
    }

    public static String monitorAvgKeyGenerator(String deviceName){
        return deviceName + "_avg";
    }
}
