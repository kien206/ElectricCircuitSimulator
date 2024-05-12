package CircuitAnalysis;

import Circuit.ElectricalCircuit;
import Elements.Capacitor;
import Elements.Elements;
import Elements.Inductor;
import Elements.Resistor;
import TableRowData.TableRowData;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class CircuitAnalysis {
    public CircuitAnalysis() {
        
    }
    public void showAnalysis(ElectricalCircuit circuit) {
        Stage primaryStage = new Stage();
        TableView<TableRowData> tableView = new TableView<>();

        TableColumn<TableRowData, String> col1 = new TableColumn<>("name");
        TableColumn<TableRowData, Double> col2 = new TableColumn<>("U");
        TableColumn<TableRowData, Double> col3 = new TableColumn<>("I");
        TableColumn<TableRowData, Double> col4 = new TableColumn<>("R");
        col1.setCellValueFactory(new PropertyValueFactory<>("name"));
        col2.setCellValueFactory(new PropertyValueFactory<>("voltage"));
        col3.setCellValueFactory(new PropertyValueFactory<>("currentIntensity"));
        col4.setCellValueFactory(new PropertyValueFactory<>("resistance"));

        tableView.getColumns().addAll(col1, col2, col3, col4);
        for (Elements element : circuit.elements) {
            if (element instanceof Resistor) {
                Resistor element1 = (Resistor) element;
                TableRowData data = new TableRowData(element1.getName(), element1.getVoltage(circuit), element1.getCurrentIntensity(circuit), element1.getResistance());
                tableView.getItems().add(data);
            } else if (element instanceof Capacitor) {
                Capacitor element1 = (Capacitor) element;
                TableRowData data = new TableRowData(element1.getName(), element1.getVoltage(circuit), element1.getCurrentIntensity(circuit), element1.getResistance(circuit));
                tableView.getItems().add(data);
            } else {
                Inductor element1 = (Inductor) element;
                TableRowData data = new TableRowData(element1.getName(), element1.getVoltage(circuit), element1.getCurrentIntensity(circuit), element1.getResistance(circuit));
                tableView.getItems().add(data);
            }
            System.out.println(element.toString());
        }
        tableView.refresh();
        // Create the scene and show the stage
        Scene scene = new Scene(tableView);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circuit Analysis Table");
        primaryStage.show();
    }
    
}
