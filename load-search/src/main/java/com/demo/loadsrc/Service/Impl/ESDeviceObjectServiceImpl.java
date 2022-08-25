package com.demo.loadsrc.Service.Impl;

import com.demo.loadsrc.Service.ESDeviceObjectService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ESDeviceObjectServiceImpl implements ESDeviceObjectService {
    @Resource
    private RestHighLevelClient client;


}
