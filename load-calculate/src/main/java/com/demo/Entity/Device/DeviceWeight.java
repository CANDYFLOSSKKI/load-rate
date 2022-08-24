package com.demo.Entity.Device;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceWeight {
    private double avgP;
    private double avgQ;
    private double sumP;
    private double sumQ;
    private double sumPDouble;

    {
        avgP = 0;
        avgQ = 0;
        sumP = 0;
        sumQ = 0;
        sumPDouble = 0;
    }

    public void addAvgP(double avgP) {
        this.avgP += avgP;
    }

    public void addAvgQ(double avgQ) {
        this.avgQ += avgQ;
    }

    public void addSumP(double sumP) {
        this.sumP += sumP;
    }

    public void addSumQ(double sumQ) {
        this.sumQ += sumQ;
    }

    public void addSumPDouble(double sumPDouble) {
        this.sumPDouble += sumPDouble;
    }

    public void addAnotherWight(DeviceWeight weight) {
        addAvgP(weight.getAvgP());
        addAvgQ(weight.getAvgQ());
        addSumP(weight.getSumP());
        addSumQ(weight.getSumQ());
        addSumPDouble(weight.getSumPDouble());
    }
}
