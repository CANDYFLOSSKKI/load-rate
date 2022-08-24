package com.demo.Entity.Factor;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FactorDataDoc {
    private String name;
    private double factor;
    private double cos;
    private double tan;
    private String type;
}
