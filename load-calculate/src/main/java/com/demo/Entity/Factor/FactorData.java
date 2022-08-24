package com.demo.Entity.Factor;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_factor")
public class FactorData{
    @TableId(value="ID")
    private Integer ID;
    private String name;
    private Double factor;
    private Double cos;
    private Double tan;
    private String type;

    public FactorData(FactorDataDoc doc){
        this.ID=0;
        this.name=doc.getName();
        this.factor=doc.getFactor();
        this.cos=doc.getCos();
        this.tan=doc.getTan();
        this.type=doc.getType();
    }
}
