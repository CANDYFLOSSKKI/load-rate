package com.demo.loadser.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class DeviceTimeFormatter {
    public static LocalDateTime format(String str){
        LocalDate date;
        LocalTime time;
        String[] strPartDateAndTime = str.split(" ");
        String[] strDate=strPartDateAndTime[0].split("-");
        String[] strTime=strPartDateAndTime[1].split(":");
        date=LocalDate.of(Integer.parseInt(strDate[0]),Integer.parseInt(strDate[1]),Integer.parseInt(strDate[2]));
        time=LocalTime.of(Integer.parseInt(strTime[0]),Integer.parseInt(strTime[1]),0);
        return LocalDateTime.of(date,time);
    }
}
