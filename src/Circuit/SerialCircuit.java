package Circuit;

import Elements.Elements;
import SourceType.VoltageSource;
import javafx.collections.FXCollections;

public class SerialCircuit extends ElectricalCircuit {
    
    public SerialCircuit(VoltageSource source) {
        super(source);
        //TODO Auto-generated constructor stub
        this.elements = FXCollections.observableArrayList();
    }

    public double getEquivalentResistance() {
        double EquivalentResistance = 0;
        for (Elements element : elements) {
            EquivalentResistance += element.getResistance();
        }
        return EquivalentResistance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return source.toString();
    }
}
