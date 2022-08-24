package com.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.Entity.KmtData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface KmtDataMapper extends BaseMapper<KmtData> {
}
