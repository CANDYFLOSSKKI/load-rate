package com.demo.Util;

import com.demo.Entity.Factor.FactorData;
import org.springframework.stereotype.Service;

@Service
public class FactorTransform {
    public FactorData transformUtoD(FactorData data){
        double value=data.getFactor();
        data.setFactor(50*Math.pow(value,5)-181.25*Math.pow(value,4)
                +254.58*Math.pow(value,3)-173.19*Math.pow(value,2)+57.95*value-7.135);
        return data;
    }
    public FactorData transformDtoU(FactorData data){
        double value=data.getFactor();
        data.setFactor(277.83*Math.pow(value,4)-64.94*Math.pow(value,5)
                -457.09*Math.pow(value,3)+363.51*Math.pow(value,2)-139.13*value+20.89);
        return data;
    }
}
