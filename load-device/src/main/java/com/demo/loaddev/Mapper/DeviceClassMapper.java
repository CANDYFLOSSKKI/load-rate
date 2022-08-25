package com.demo.loaddev.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.loaddev.TableEntity.DeviceClass;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeviceClassMapper extends BaseMapper<DeviceClass> {
}
