package TableRowData;

public class TableRowData {
    private String name;
    private double voltage;
    private double currentIntensity;
    private double resistance;

    public TableRowData(String name, double voltage, double currentIntensity, double resistance) {
        this.name = name;
        this.voltage = voltage;
        this.currentIntensity = currentIntensity;
        this.resistance = resistance;
    }

    public String getName() {
        return name;
    }

    public double getVoltage() {
        return voltage;
    }

    public double getCurrentIntensity() {
        return currentIntensity;
    }

    public double getResistance() {
        return resistance;
    }
    
    
}
