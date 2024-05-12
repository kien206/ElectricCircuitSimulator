package Elements;

import Circuit.ElectricalCircuit;

public abstract class Elements {
    protected double voltage;
    protected double currentIntensity;
    protected double resistance;
    protected String name;

    public String getName() {
        return name;
    }
    public abstract double getVoltage(ElectricalCircuit circuit);
    public abstract double getCurrentIntensity(ElectricalCircuit circuit);
    public double getResistance() {
        return resistance;
    };
    public abstract String toString();
}
