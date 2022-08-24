package com.demo.Service.Data.Imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.Entity.Factor.FactorBundle;
import com.demo.Entity.Factor.FactorData;
import com.demo.Entity.Factor.FactorDataDoc;
import com.demo.Mapper.FactorDataMapper;
import com.demo.Service.Data.FactorDataService;
import com.demo.Entity.Response.FactorDataResp;
import com.demo.Util.FactorTransform;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

@Service
public class FactorDataServiceImp extends ServiceImpl<FactorDataMapper, FactorData> implements FactorDataService {
    @Resource
    private FactorDataMapper mapper;
    @Resource
    private FactorTransform factorTransform;

    @Override
    public FactorDataResp addFactorData(FactorDataDoc doc) {
        mapper.insert(new FactorData(doc));
        String stb = "成功添加对" + doc.getName() + "类型的" +
                (doc.getType().equalsIgnoreCase("D") ? "需要系数" : "利用系数") + "及功率因数参数";
        return new FactorDataResp(true, stb, null);
    }

    @Override
    public FactorDataResp delFactorData(String name, String type) {
        LambdaQueryWrapper<FactorData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FactorData::getName, name).eq(FactorData::getType, type);
        int sign = mapper.delete(wrapper);
        if (sign == 1) {
            return new FactorDataResp(false, "未查找到匹配参数", null);
        }
        return new FactorDataResp(true, "删除成功", null);
    }

    @Override
    public FactorDataResp getFactorData(String name, String type) {
        LambdaQueryWrapper<FactorData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FactorData::getName, name).eq(FactorData::getType, type);
        FactorData data = mapper.selectOne(wrapper);
        if (data == null) {
            return new FactorDataResp(false, "未查找到匹配参数", null);
        }
        return new FactorDataResp(true, "已查找到匹配参数", data);
    }

    @Override
    public FactorBundle getFactorDataForMethod(String name, String type) {
        FactorDataResp resp = getFactorData(name, type);
        FactorData data = (FactorData) resp.getData();
        if (!resp.isSign()) {
            if (type.equals("D")) {
                resp = getFactorData(name, "U");
                data = factorTransform.transformUtoD((FactorData) resp.getData());
            } else {
                resp = getFactorData(name, "D");
                data = factorTransform.transformDtoU((FactorData) resp.getData());
            }
        }
        return new FactorBundle(data);
    }

    @Override
    public FactorDataResp updateFactorData(FactorDataDoc doc) {
        FactorDataResp resp = getFactorData(doc.getName(), doc.getType());
        if (!resp.isSign()) {
            return resp;
        }
        FactorData data = new FactorData(doc);
        data.setID(((FactorData) resp.getData()).getID());
        LambdaUpdateWrapper<FactorData> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(FactorData::getName, doc.getName()).eq(FactorData::getType, doc.getType());
        mapper.update(data, wrapper);
        return new FactorDataResp(true, "更新成功", null);
    }

    @Override
    public Set<String> showAllFactorData() {
        LambdaQueryWrapper<FactorData> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(FactorData::getType, "D");
        Set<String> set = new HashSet<>(mapper.selectList(wrapper).stream().map(FactorData::getName).toList());
        wrapper.clear();
        wrapper.eq(FactorData::getType, "U");
        set.addAll(mapper.selectList(wrapper).stream().map(FactorData::getName).toList());
        return set;
    }
}
