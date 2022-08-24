package com.demo.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.Entity.Factor.FactorData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface FactorDataMapper extends BaseMapper<FactorData> {
}
