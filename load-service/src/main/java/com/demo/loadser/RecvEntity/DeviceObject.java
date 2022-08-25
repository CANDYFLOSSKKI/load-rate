package com.demo.loadser.RecvEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceObject {
    private Integer ID;
    private String name;        //设备名称
    private String version;     //设备编号
    private Integer classid;    //设备所属类别ID
    private Double ratedpower;  //设备额定功率
}
