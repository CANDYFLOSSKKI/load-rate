package com.demo.loadmes.Service;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeviceMonitorUtils {
    public static String monitorTimeKeyGenerator(String deviceName){
        return deviceName + "_time";
    }

    public static String monitorAvgKeyGenerator(String deviceName){
        return deviceName + "_avg";
    }

    public static LocalDateTime monitorTimeStrFormatter(String timestr){
        String[] strPartDateAndTime=timestr.split(" ");
        String[] strPartDate=strPartDateAndTime[0].split("-");
        LocalDate date=LocalDate.of(Integer.parseInt(strPartDate[0]),Integer.parseInt(strPartDate[1]),Integer.parseInt(strPartDate[2]));
        LocalTime time;
        String[] strPartTime=strPartDateAndTime[1].split(":");
        time=LocalTime.of(Integer.parseInt(strPartTime[0]),Integer.parseInt(strPartTime[1]),Integer.parseInt(strPartTime[2]));
        return LocalDateTime.of(date,time);
    }
}
