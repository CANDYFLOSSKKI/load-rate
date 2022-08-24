package com.demo.Entity.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculateResp {
    private boolean sign;
    private String method;
    private String unit;
    private Result result;
}
