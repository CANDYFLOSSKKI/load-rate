package com.demo.loaddev.Service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.loaddev.Entity.Table.DeviceClass;
import com.demo.loaddev.Entity.DeviceClassDoc;
import com.demo.loaddev.Mapper.DeviceClassMapper;
import com.demo.loaddev.Service.DeviceClassService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceClassServiceImpl extends ServiceImpl<DeviceClassMapper, DeviceClass> implements DeviceClassService {
    @Resource
    private DeviceClassMapper mapper;

    @Override
    public void addDeviceClass(DeviceClassDoc doc) {
        mapper.insert(new DeviceClass(doc));
    }

    @Override
    public void delDeviceClass(Integer id) {
        mapper.deleteById(id);
    }

    @Override
    public void updateDeviceClass(DeviceClass doc) {
        mapper.updateById(doc);
    }

    @Override
    public void updateDeviceClass(DeviceClassDoc doc) {
        LambdaQueryWrapper<DeviceClass> wrapper_query=new LambdaQueryWrapper<>();
        wrapper_query.eq(DeviceClass::getName,doc.getName());
        DeviceClass newClass=new DeviceClass(doc);
        newClass.setID(mapper.selectOne(wrapper_query).getID());
        updateDeviceClass(newClass);
    }

    @Override
    public DeviceClass getDeviceClass(Integer id) {
        return mapper.selectById(id);
    }

    @Override
    public List<DeviceClass> getDeviceClass(List<Integer> ids) {
        LambdaQueryWrapper<DeviceClass> wrapper=new LambdaQueryWrapper<>();
        wrapper.in(DeviceClass::getID,ids);
        return mapper.selectList(wrapper);
    }
}
