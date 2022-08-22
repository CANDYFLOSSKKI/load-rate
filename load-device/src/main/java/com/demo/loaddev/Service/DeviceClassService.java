package com.demo.loaddev.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.loaddev.Entity.Table.DeviceClass;
import com.demo.loaddev.Entity.DeviceClassDoc;

import java.util.List;

public interface DeviceClassService extends IService<DeviceClass> {
    void addDeviceClass(DeviceClassDoc doc);
    void delDeviceClass(Integer id);
    void updateDeviceClass(DeviceClass doc);
    void updateDeviceClass(DeviceClassDoc doc);

    DeviceClass getDeviceClass(Integer id);
    List<DeviceClass> getDeviceClass(List<Integer> ids);
}
