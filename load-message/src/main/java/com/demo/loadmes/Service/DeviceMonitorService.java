package com.demo.loadmes.Service;

import java.time.LocalDateTime;
import java.util.Map;

public interface DeviceMonitorService {
    void startDeviceMonitor(String deviceName);
    void stopDeviceMonitor(String deviceName);

    void startMonitorService();
    void stopMonitorService();

    void scheduledMonitor();
    void scheduledMonitorEnd();
    void scheduledMonitorReduce(String deviceName);

    Map<LocalDateTime, Double> getDeviceMonitorData(String deviceName);
}
