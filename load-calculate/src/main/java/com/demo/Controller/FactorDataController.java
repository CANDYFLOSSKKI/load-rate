package com.demo.Controller;

import com.demo.Entity.Factor.FactorDataDoc;
import com.demo.Service.Data.FactorDataService;
import com.demo.Entity.Response.FactorDataResp;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class FactorDataController {
    @Resource
    private FactorDataService service;

    @PostMapping("/add")
    public FactorDataResp addFactorData(@RequestBody FactorDataDoc doc){
        return service.addFactorData(doc);
    }

    @GetMapping("/get")
    public FactorDataResp getFactorData(@RequestParam String name,@RequestParam String type){
        return service.getFactorData(name,type);
    }

    @GetMapping("/del")
    public FactorDataResp delFactorData(@RequestParam String name,@RequestParam String type){
        return service.delFactorData(name,type);
    }

    @PostMapping("/update")
    public FactorDataResp updateFactorData(@RequestBody FactorDataDoc doc){
        return null;
    }

    @GetMapping("/show")
    public ArrayList<String> getAllFactorData(){
        return new ArrayList<>(service.showAllFactorData());
    }
}
