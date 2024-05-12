package Circuit;

import Elements.Elements;
import SourceType.VoltageSource;
import javafx.collections.FXCollections;

public class ParallelCircuit extends ElectricalCircuit {
  
    public ParallelCircuit(VoltageSource source) {
        super(source);
        //TODO Auto-generated constructor stub
        this.elements = FXCollections.observableArrayList();
    }

    public double getEquivalentResistance() {
        double EquivalentResistance = 0;
        for (Elements element : elements) {
            EquivalentResistance += 1/(element.getResistance());
        }
        return 1/EquivalentResistance;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return source.toString();
    }  
}
