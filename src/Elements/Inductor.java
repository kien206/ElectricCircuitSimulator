package Elements;

import Circuit.ElectricalCircuit;
import Circuit.SerialCircuit;
import ComplexNumber.ComplexNumber;
import SourceType.DCVoltageSource;

public class Inductor extends Elements {
    private double inductance;
    
    public Inductor(String name, double inductance) {
        this.name = name;
        this.inductance = inductance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Inductor: " + String.valueOf(inductance); 
    }

    public double getInductance() {
        return inductance*1e-6;
    }

    public double getVoltage(ElectricalCircuit circuit) {
        if (circuit instanceof SerialCircuit) {
            return getCurrentIntensity(circuit) * getResistance(circuit);
        } else {
            return circuit.source.getVoltage();
        }
    }

    public double getCurrentIntensity(ElectricalCircuit circuit) {
        if (circuit instanceof SerialCircuit) {
            return circuit.source.getVoltage() / circuit.getEquivalentResistance();
        } else {
            return circuit.source.getVoltage() / this.getResistance(circuit);
        }
    }

    public void setInductance(double inductance) {
        this.inductance = inductance;
    }
    
    public double getResistance(ElectricalCircuit circuit) {
        if (circuit.source instanceof DCVoltageSource) {
            return 0;
        } else {
            ComplexNumber impedance = new ComplexNumber(0, 1/(2*Math.PI*circuit.source.getFrequency()*inductance));
            return Math.round(impedance.getImaginary()*100)/100;
        }
    }
}
