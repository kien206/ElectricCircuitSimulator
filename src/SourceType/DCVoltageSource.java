package SourceType;

public class DCVoltageSource extends VoltageSource {

    public DCVoltageSource(double voltage) {
        super(voltage);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "DC" + " Voltage: " + Double.valueOf(voltage);
    }
    
}