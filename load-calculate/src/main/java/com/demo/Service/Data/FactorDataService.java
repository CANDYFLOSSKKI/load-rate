package com.demo.Service.Data;

import com.baomidou.mybatisplus.extension.service.IService;
import com.demo.Entity.Factor.FactorBundle;
import com.demo.Entity.Factor.FactorData;
import com.demo.Entity.Factor.FactorDataDoc;
import com.demo.Entity.Response.FactorDataResp;

import java.util.Set;

public interface FactorDataService extends IService<FactorData>{

    FactorDataResp addFactorData(FactorDataDoc doc);
    FactorDataResp getFactorData(String name,String type);
    FactorDataResp delFactorData(String name,String type);

    FactorBundle getFactorDataForMethod(String name,String type);

    FactorDataResp updateFactorData(FactorDataDoc doc);

    Set<String> showAllFactorData();
}
