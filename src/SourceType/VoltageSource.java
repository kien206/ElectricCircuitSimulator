package SourceType;

public abstract class VoltageSource {
    protected double voltage;
    protected double frequency;

    public VoltageSource(double voltage) {
        this.voltage = voltage;
    }

    public double getVoltage() {
        return voltage;
    }

    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    public double getFrequency() {
        return frequency;
    }

    public abstract String toString();
}
