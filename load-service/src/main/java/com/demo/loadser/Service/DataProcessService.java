package com.demo.loadser.Service;

import com.demo.loadser.RespEntity.DeviceStatus.DeviceClassAndObjectBranch;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusRequest;
import com.demo.loadser.RespEntity.DeviceStatus.DeviceStatusResponse;

import java.util.List;

public interface DataProcessService {

    List<DeviceClassAndObjectBranch> getDeviceBranchList();

    DeviceStatusResponse getDeviceStatus(DeviceStatusRequest req);


}
