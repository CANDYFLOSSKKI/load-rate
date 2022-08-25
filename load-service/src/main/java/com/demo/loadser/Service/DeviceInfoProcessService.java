package com.demo.loadser.Service;

import com.demo.loadser.RespDeviceInfoEntity.DeviceClassAndObjectBranch;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusRequest;
import com.demo.loadser.RespDeviceInfoEntity.DeviceStatusResponse;

import java.util.List;

public interface DeviceInfoProcessService {

    List<DeviceClassAndObjectBranch> getDeviceBranchList();

    DeviceStatusResponse getDeviceStatus(DeviceStatusRequest req);


}
