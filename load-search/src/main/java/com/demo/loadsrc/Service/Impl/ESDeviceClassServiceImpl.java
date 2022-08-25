package com.demo.loadsrc.Service.Impl;

import com.demo.loadsrc.Service.ESDeviceClassService;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ESDeviceClassServiceImpl implements ESDeviceClassService {
    @Resource
    private RestHighLevelClient client;

}
