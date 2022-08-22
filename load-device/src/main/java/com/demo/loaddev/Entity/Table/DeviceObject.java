package com.demo.loaddev.Entity.Table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.demo.loaddev.Entity.DeviceObjectDoc;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_device_object")
public class DeviceObject {
    @TableId(value="ID")
    private Integer ID;
    private String name;        //设备名称
    private String version;     //设备编号
    @TableField("classid")
    private Integer classid;    //设备所属类别ID
    @TableField("ratedpower")
    private Double ratedpower;  //设备额定功率

    public DeviceObject(DeviceObjectDoc doc){
        this.ID=0;
        this.name=doc.getName();
        this.version=doc.getVersion();
        this.classid=doc.getClassid();
        this.ratedpower=doc.getRatedpower();
    }
}
