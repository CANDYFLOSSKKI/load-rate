package com.demo.Service.Data;


import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.Entity.KmtData;

public interface KmtDataService extends IService<KmtData> {
    double kmtDataMatch(int row,int column);
}
