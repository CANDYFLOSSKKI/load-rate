package com.demo.loaddev.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.loaddev.TableEntity.*;
import com.demo.loaddev.TableEntity.DeviceClass;
import com.demo.loaddev.TableEntity.DeviceObject;

import java.util.List;

public interface DeviceObjectService extends IService<DeviceObject> {

    void addDeviceObject(DeviceObjectDoc doc);
    void delDeviceObject(Integer id);
    void updateDeviceObject(DeviceObject doc);
    void updateDeviceObject(DeviceObjectDoc doc);

    DeviceObject getDeviceObject(Integer id);
    DeviceClass getDeviceObjectConnectedClass(Integer id);
    List<DeviceObject> getDeviceObject(List<Integer> ids);

    DeviceObject getDeviceObjectByName(String name);
}
