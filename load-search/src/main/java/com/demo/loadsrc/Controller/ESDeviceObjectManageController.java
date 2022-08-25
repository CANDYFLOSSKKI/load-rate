package com.demo.loadsrc.Controller;

import com.demo.loadsrc.Service.ESDeviceObjectService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ESDeviceObjectManageController {
    @Resource
    private ESDeviceObjectService service;

}
