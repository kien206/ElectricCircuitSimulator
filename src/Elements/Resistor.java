package Elements;

import Circuit.ElectricalCircuit;
import Circuit.ParallelCircuit;

public class Resistor extends Elements {
    public Resistor(String name, double resistance) {
        this.name = name;
        this.resistance = resistance;
    }

    public double getResistance() {
        return this.resistance;
    }
    
    public void setResistance(double resistance) {
        this.resistance = resistance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Resistor: " + String.valueOf(resistance);
    }

    @Override
    public double getVoltage(ElectricalCircuit circuit) {
        if (circuit instanceof ParallelCircuit) {
            return circuit.source.getVoltage();
        } else {
            return this.getCurrentIntensity(circuit) * this.getResistance();
        }
    }

    @Override
    public double getCurrentIntensity(ElectricalCircuit circuit) {
        if (circuit instanceof ParallelCircuit) {
            return circuit.source.getVoltage() / this.getResistance(); 
        } else {
            return circuit.source.getVoltage() / circuit.getEquivalentResistance();
        }
    }
    
}
