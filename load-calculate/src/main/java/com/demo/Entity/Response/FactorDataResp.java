package com.demo.Entity.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactorDataResp {
    private boolean sign;
    private String message;
    private Object data;
}
