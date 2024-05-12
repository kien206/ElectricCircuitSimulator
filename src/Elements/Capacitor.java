package Elements;

import Circuit.ElectricalCircuit;
import Circuit.SerialCircuit;
import ComplexNumber.ComplexNumber;
import SourceType.DCVoltageSource;

public class Capacitor extends Elements {
    private double capacitance;

    public Capacitor(String name, double capacitance) {
        this.name = name;
        this.capacitance = capacitance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Capacitor: " + String.valueOf(capacitance);
    }

    public double getCapacitance() {
        return capacitance*1e-6;
    }

    public void setCapacitance(double capacitance) {
        this.capacitance = capacitance;
    }

    @Override
    public double getVoltage(ElectricalCircuit circuit) {
        if (circuit instanceof SerialCircuit) {
            return getCurrentIntensity(circuit) * getResistance(circuit);
        } else {
            return circuit.source.getVoltage();
        }
    }

    @Override
    public double getCurrentIntensity(ElectricalCircuit circuit) {
        if (circuit instanceof SerialCircuit) {
            return circuit.source.getVoltage() / circuit.getEquivalentResistance();
        } else {
            return 0;
        }
    }
    
    
    public double getResistance(ElectricalCircuit circuit) {
        if (circuit.source instanceof DCVoltageSource) {
            return Double.POSITIVE_INFINITY;
        } else {
            ComplexNumber impedance = new ComplexNumber(0, -2 * Math.PI * circuit.source.getFrequency() * capacitance);
            return Math.round(impedance.getImaginary()*100)/100;
        }
    }

}
