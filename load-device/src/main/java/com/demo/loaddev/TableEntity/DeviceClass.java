package com.demo.loaddev.TableEntity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_device_class")
public class DeviceClass {
    @TableId(value="ID")
    private Integer ID;
    private String name;        //设备类型名
    private String version;     //设备型号
    private Integer mode;       //设备工作制
    private Double cycle;       //设备负载持续率(可null)

    public DeviceClass(DeviceClassDoc doc){
        this.ID=0;
        this.name=doc.getName();
        this.version=doc.getVersion();
        this.mode=doc.getMode();
        if(this.mode==0){
            this.cycle=100.00;
        }else{
            this.cycle=doc.getCycle();
        }
    }
}
