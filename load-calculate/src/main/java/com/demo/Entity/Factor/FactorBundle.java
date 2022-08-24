package com.demo.Entity.Factor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactorBundle {
    private double factor;
    private double cos;
    private double tan;

    public FactorBundle(FactorData data){
        this.factor=data.getFactor();
        this.cos= data.getCos();
        this.tan= data.getTan();
    }
}
