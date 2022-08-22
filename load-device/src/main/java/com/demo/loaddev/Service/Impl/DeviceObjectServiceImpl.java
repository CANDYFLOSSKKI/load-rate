package com.demo.loaddev.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.loaddev.Entity.*;
import com.demo.loaddev.Entity.Table.DeviceClass;
import com.demo.loaddev.Entity.Table.DeviceObject;
import com.demo.loaddev.Mapper.DeviceObjectMapper;
import com.demo.loaddev.Message.MessageService;
import com.demo.loaddev.Service.DeviceClassService;
import com.demo.loaddev.Service.DeviceObjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeviceObjectServiceImpl extends ServiceImpl<DeviceObjectMapper, DeviceObject> implements DeviceObjectService {
    @Resource
    private DeviceObjectMapper mapper;

    @Resource
    private MessageService messageService;

    @Resource
    private DeviceClassService deviceClassService;

    @Override
    public void addDeviceObject(DeviceObjectDoc doc) {
        mapper.insert(new DeviceObject(doc));
        messageService.addDeviceMonitor(doc.getName());
    }

    @Override
    public void delDeviceObject(Integer id) {
        messageService.delDeviceMonitor(getDeviceObject(id).getName());
        mapper.deleteById(id);
    }

    @Override
    public void updateDeviceObject(DeviceObject doc) {
        mapper.updateById(doc);
    }

    @Override
    public void updateDeviceObject(DeviceObjectDoc doc) {
        LambdaQueryWrapper<DeviceObject> wrapper_query=new LambdaQueryWrapper<>();
        wrapper_query.eq(DeviceObject::getName,doc.getName());
        DeviceObject newObject=new DeviceObject(doc);
        newObject.setID(mapper.selectOne(wrapper_query).getID());
        updateDeviceObject(newObject);
    }

    @Override
    public DeviceObject getDeviceObject(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<DeviceObject> getDeviceObject(List<Integer> ids) {
        LambdaQueryWrapper<DeviceObject> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(DeviceObject::getID,ids);
        return mapper.selectList(wrapper);
    }
    @Override
    public DeviceClass getDeviceObjectConnectedClass(Integer id) {
        DeviceObject object=mapper.selectById(id);
        return deviceClassService.getDeviceClass(object.getClassid());
    }

    @Override
    public DeviceObject getDeviceObjectByName(String name) {
        LambdaQueryWrapper<DeviceObject> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(DeviceObject::getName,name);
        return mapper.selectOne(wrapper);
    }
}
