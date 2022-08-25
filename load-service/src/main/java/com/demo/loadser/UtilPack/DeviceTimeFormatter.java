package com.demo.loadser.UtilPack;

import java.time.LocalDateTime;

public class DeviceTimeFormatter {
    public static LocalDateTime format(String str){
        String[] strPartDateAndTime = str.split(" ");
        String[] strDate=strPartDateAndTime[0].split("-");
        String[] strTime=strPartDateAndTime[1].split(":");
        return LocalDateTime.of(
                Integer.parseInt(strDate[0]),
                Integer.parseInt(strDate[1]),
                Integer.parseInt(strDate[2]),
                Integer.parseInt(strTime[0]),
                Integer.parseInt(strTime[1]),
                0
        );
    }
}
