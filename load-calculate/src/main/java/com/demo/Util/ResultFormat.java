package com.demo.Util;

import com.demo.Entity.Device.DeviceDoc;
import com.demo.Entity.Response.Result;

import java.text.NumberFormat;

public class ResultFormat {
    private static final NumberFormat formatter = NumberFormat.getInstance();

    static {
        formatter.setMinimumFractionDigits(2);
    }

    public static Result format(double pc, double qc) {
        double sc = Math.hypot(pc, qc);
        return new Result(
                Double.parseDouble(formatter.format(pc)),
                Double.parseDouble(formatter.format(qc)),
                Double.parseDouble(formatter.format(sc))
        );
    }

    public static double format(double num) {
        return Double.parseDouble(formatter.format(num));
    }


    public static DeviceDoc format(DeviceDoc doc) {
        doc.setData(doc.getData().stream().peek(data -> data.setCount(data.getCount() / 1000)).toList());
        return doc;
    }
}
