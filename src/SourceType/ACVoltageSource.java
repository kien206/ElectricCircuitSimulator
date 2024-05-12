package SourceType;

public class ACVoltageSource extends VoltageSource {

    public ACVoltageSource(double voltage, double frequency) {
        super(voltage);
        this.frequency = frequency;
    }
    
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getVoltage(double time) {
        return voltage * Math.sin(2 * Math.PI * frequency * time);
    }

    public double getCurrent(double time) {
        return getVoltage(time);
    }
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "AC" + " Voltage: " + Double.valueOf(voltage) + " Frequency: " + Double.valueOf(frequency);
    }
}
