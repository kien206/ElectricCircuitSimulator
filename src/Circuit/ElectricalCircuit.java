package Circuit;

import java.util.ArrayList;
import java.util.Iterator;

import Elements.Elements;
import Elements.Resistor;
import SourceType.ACVoltageSource;
import SourceType.VoltageSource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public abstract class ElectricalCircuit {
    public ObservableList<Elements> elements;
    public VoltageSource source;
    public double frequency;

    public void setSource(VoltageSource source) {
        this.source = source;
    }

    public ElectricalCircuit(VoltageSource source) {
        this.source = source;
    }

    public void addElements(Elements element) {
        elements.add(element);
    }

    public void removeElements(Elements element) {
        elements.remove(element);
    }

    public void clearElements() {
        Iterator<Elements> iterator = elements.iterator();
        while (iterator.hasNext()) {
            Elements item = iterator.next();
            // Perform your logic
            iterator.remove();  // Remove the element using the iterator
        }

    }


    public abstract double getEquivalentResistance();
    
    public boolean checkShortCircuit() {
        for (Elements element : this.elements) {
            if (element instanceof Resistor && element.getResistance() == 0) {
                return true;
            }
        }
        return false;
    }

    public abstract String toString();
}

