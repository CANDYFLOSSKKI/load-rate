package com.demo.Service.Data.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Entity.KmtData;
import com.demo.Mapper.KmtDataMapper;
import com.demo.Service.Data.KmtDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KmtDataServiceImp extends ServiceImpl<KmtDataMapper, KmtData> implements KmtDataService {
    @Resource
    private KmtDataMapper mapper;
    @Override
    public double kmtDataMatch(int row, int column) {
        KmtData data=mapper.selectById(row);
        String[] strList=data.getStr().split("/");
        return Double.parseDouble(strList[column]);
    }
}
