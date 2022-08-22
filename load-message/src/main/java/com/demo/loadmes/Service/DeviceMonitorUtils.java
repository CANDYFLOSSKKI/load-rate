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
        if(strPartDateAndTime[1].contains(":")){
            String[] strPartTime=strPartDateAndTime[1].split(":");
            time=LocalTime.of(Integer.parseInt(strPartTime[0]),Integer.parseInt(strPartTime[1]),0);
        }else{
            time=LocalTime.of(Integer.parseInt(strPartDateAndTime[1]),0,0);
        }
        return LocalDateTime.of(date,time);
    }
}
