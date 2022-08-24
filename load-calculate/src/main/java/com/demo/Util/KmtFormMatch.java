package com.demo.Util;

import com.demo.Service.Data.KmtDataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class KmtFormMatch {
    @Resource
    private KmtDataService service;
    private static final double[] NeqMatchline = new double[]{
            4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18, 20, 25, 30, 35, 40, 45, 50, 60, 70, 80, 90, 100, 120, 160, 200, 240
    };
    private static final double[] KutMatchline = new double[]{
            0.1, 0.15, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8
    };

    public double match(double Neq, double Kut,int crossSection) {
        int row = 1, column = 1;
        while (true) {
            if (Neq < NeqMatchline[row]) {
                if (!(Neq > ((NeqMatchline[row] + NeqMatchline[row - 1]) / 2))) {
                    --row;
                }
                break;
            }
            ++row;
        }
        while (true) {
            if (Kut < KutMatchline[column]) {
                if (!(Kut > ((KutMatchline[column] + KutMatchline[column - 1]) / 2))) {
                    --column;
                }
                break;
            }
            ++column;
        }
        double kmt=service.kmtDataMatch(row,column);
        switch(crossSection){
            case 1->{ kmt=1+(kmt-1)/Math.sqrt(2); }
            case 2->{ kmt=1+(kmt-1)/2; }
        }
        return kmt;
    }
}
