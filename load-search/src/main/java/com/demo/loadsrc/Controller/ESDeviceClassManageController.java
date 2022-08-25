package com.demo.loadsrc.Controller;

import com.demo.loadsrc.Service.ESDeviceClassService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ESDeviceClassManageController {
    @Resource
    private ESDeviceClassService service;

}
