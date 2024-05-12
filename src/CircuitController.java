
import Circuit.ElectricalCircuit;
import Circuit.ParallelCircuit;
import Circuit.SerialCircuit;
import CircuitAnalysis.CircuitAnalysis;
import CircuitDiagram.CircuitDiagram;
import SourceType.ACVoltageSource;
import SourceType.DCVoltageSource;
import SourceType.VoltageSource;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.layout.GridPane;

import Elements.*;
public class CircuitController {
    private double voltage;
    private double frequency;
    public VoltageSource source = new ACVoltageSource(0,0);
    public ElectricalCircuit circuit = new ParallelCircuit(source);
    public ObservableList<Elements> elements = FXCollections.observableArrayList();
    
    // Parallel [Resistor, Capacitor, Inductor]
    // Serial   [Resistor, Capacitor, Inductor]
    private int countSerial = 0; // count the number of components respectively
    private int countParallel = 0;
    // Store values of components
    private ObservableList<Double> parallelResistorValues = FXCollections.observableArrayList();
    private ObservableList<Double> parallelCapacitorValues = FXCollections.observableArrayList();
    private ObservableList<Double> parallelInductorValues = FXCollections.observableArrayList();
    private ObservableList<Double> serialResistorValues = FXCollections.observableArrayList();
    private ObservableList<Double> serialCapacitorValues = FXCollections.observableArrayList();
    private ObservableList<Double> serialInductorValues = FXCollections.observableArrayList();

    @FXML
    private TabPane tabPane;
    
    @FXML
    private Button btnAnalyzeParallel;

    @FXML
    private Button btnAnalyzeSerial;

    @FXML
    private Button btnAddCapacitorParallel;

    @FXML
    private Button btnAddCapacitorSerial;

    @FXML
    private Button btnAddInductorParallel;

    @FXML
    private Button btnAddInductorSerial;

    @FXML
    private Button btnAddResistorParallel;

    @FXML
    private Button btnAddResistorSerial;

    @FXML
    private Button btnRemoveCapacitorParallel;

    @FXML
    private Button btnRemoveCapacitorSerial;

    @FXML
    private Button btnRemoveInductorParallel;

    @FXML
    private Button btnRemoveInductorSerial;

    @FXML
    private Button btnRemoveResistorParallel;

    @FXML
    private Button btnRemoveResistorSerial;

    @FXML
    private Button btnCreateCircuitSerial;

    @FXML
    private Button btnCreateCircuitParallel;

    @FXML
    private Tab tabParallel;

    @FXML
    private Tab tabSerial;

    @FXML
    private TextField tfCapacitor;

    @FXML
    private TextField tfInductor;

    @FXML
    private TextField tfResistor;

    @FXML
    private GridPane gridParallel;

    @FXML
    private GridPane gridSerial;

    @FXML
    private Label labelFrequencyParallel;

    @FXML
    private Label labelFrequencySerial;

    @FXML
    private TextField tfFrequencyParallel;

    @FXML
    private TextField tfFrequencySerial;

    @FXML
    private TextField tfVoltageParallel;

    @FXML
    private TextField tfVoltageSerial;

    @FXML
    private RadioButton radioParallelAC;

    @FXML
    private RadioButton radioParallelDC;

    @FXML
    private RadioButton radioSerialAC;

    @FXML
    private RadioButton radioSerialDC;

    @FXML
    private ToggleGroup sourceGroup;

    @FXML
    void btnAddCapacitorParallelPressed(ActionEvent event) {
        countParallel++;
        if (countParallel > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

            // Handle the user's response
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("OK button pressed");
            } else {
                System.out.println("Dialog closed without pressing OK");
            }
        } else {
            HBox hbox = new HBox();
            Label label = new Label("C" + Integer.toString(countParallel));
            Label capacitorLabel = new Label("\u03BCF" );
            TextField tf = new TextField();
            modify(label, tf, capacitorLabel, hbox);
            tf.setOnAction(e -> parallelCapacitorTfChanged(tf));
            VBox vbox = (VBox) gridParallel.getChildren().get(1);
            // Find the index of the button in the VBox
            int buttonIndex = vbox.getChildren().indexOf(btnAddCapacitorParallel);

            // Insert the new HBox before the button
            vbox.getChildren().add(buttonIndex, hbox);
            Capacitor capacitor = new Capacitor(label.getText(), 0);
            circuit.addElements(capacitor);
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                capacitor.setCapacitance(value*1e-6);
            });
        }
        
    }

    @FXML
    void btnAddCapacitorSerialPressed(ActionEvent event) {
        countSerial++;
        if (countSerial > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

            // Handle the user's response
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("OK button pressed");
            } else {
                System.out.println("Dialog closed without pressing OK");
            }
        } else {
            HBox hbox = new HBox();
            Label label = new Label("C" + Integer.toString(countSerial));
            Label capacitorLabel = new Label("\u03BCF");
            TextField tf = new TextField();
            modify(label, tf, capacitorLabel, hbox);
            tf.setOnAction(e -> serialCapacitorTfChanged(tf));
            
            VBox vbox = (VBox) gridSerial.getChildren().get(1);
            // Find the index of the button in the VBox
            int buttonIndex = vbox.getChildren().indexOf(btnAddCapacitorSerial);

            // Insert the new HBox before the button
            vbox.getChildren().add(buttonIndex, hbox);
            Capacitor capacitor = new Capacitor(label.getText(), 0);
            circuit.addElements(capacitor);
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                capacitor.setCapacitance(value*1e-6);
            });
        }
    }

    @FXML
    void btnAddInductorParallelPressed(ActionEvent event) {
        countParallel++;
        if (countParallel > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

        } else {
            if ((radioParallelDC).isSelected()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("WARNING");
                alert.setContentText("This will cause a short circuit");
                alert.showAndWait();
                countParallel--;
            } else {
                HBox hbox = new HBox();
                Label label = new Label("L" + Integer.toString(countParallel));
                Label inductorLabel = new Label("\u03BCH");
                TextField tf = new TextField();
                modify(label, tf, inductorLabel, hbox);
                tf.setOnAction(e -> parallelInductorTfChanged(tf));
                
                VBox vbox = (VBox) gridParallel.getChildren().get(2);
                // Find the index of the button in the VBox
                int buttonIndex = vbox.getChildren().indexOf(btnAddInductorParallel);

                // Insert the new HBox before the button
                vbox.getChildren().add(buttonIndex, hbox);
                Inductor inductor = new Inductor(label.getText(), 0);
                circuit.addElements(inductor);
                tf.textProperty().addListener((observable, oldValue, newValue) -> {
                    double value = Double.parseDouble(newValue);
                    inductor.setInductance(value*1e-6);
                });
            }   
        }
    }

    @FXML
    void btnAddInductorSerialPressed(ActionEvent event) {
        countSerial++;
        if (countSerial > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

            // Handle the user's response
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("OK button pressed");
            } else {
                System.out.println("Dialog closed without pressing OK");
            }
        } else {
            HBox hbox = new HBox();
            Label label = new Label("L" + Integer.toString(countSerial));
            Label inductorLabel = new Label("\u03BCH");
            TextField tf = new TextField();
            modify(label, tf, inductorLabel, hbox);
            tf.setOnAction(e->serialInductorTfChanged(tf));

            VBox vbox = (VBox) gridSerial.getChildren().get(2);
            // Find the index of the button in the VBox
            int buttonIndex = vbox.getChildren().indexOf(btnAddInductorSerial);

            // Insert the new HBox before the button
            vbox.getChildren().add(buttonIndex, hbox);
            Inductor inductor = new Inductor(label.getText(), 0);
            circuit.addElements(inductor);
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                inductor.setInductance(value*1e-6);
            });
        }
        
    }

    @FXML
    void btnAddResistorParallelPressed(ActionEvent event) {
        countParallel++;
        if (countParallel > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

            // Handle the user's response
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("OK button pressed");
            } else {
                System.out.println("Dialog closed without pressing OK");
            }
        } else {
            HBox hbox = new HBox();
            Label label = new Label("R" + Integer.toString(countParallel));
            Label ohm = new Label("\u03A9");
            TextField tf = new TextField();
            modify(label, tf, ohm, hbox);
            tf.setOnAction(e -> parallelResistorTfChanged(tf));
            
            VBox vbox = (VBox) gridParallel.getChildren().get(0);
            // Find the index of the button in the VBox
            int buttonIndex = vbox.getChildren().indexOf(btnAddResistorParallel);

            // Insert the new HBox before the button
            vbox.getChildren().add(buttonIndex, hbox);
            elements.add(new Resistor(label.getText(), 0));
            Resistor resistor = new Resistor(label.getText(), 0);
            circuit.addElements(resistor);
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                resistor.setResistance(value);
            });
        }
        
    }

    @FXML
    void btnAddResistorSerialPressed(ActionEvent event) {
        countSerial++;
        if (countSerial > 5) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Only 5 elements allowed");
            alert.showAndWait();

            // Handle the user's response
            if (alert.getResult() == ButtonType.OK) {
                System.out.println("OK button pressed");
            } else {
                System.out.println("Dialog closed without pressing OK");
            }
        } else {
            HBox hbox = new HBox();
            Label label = new Label("R" + Integer.toString(countSerial));
            Label ohm = new Label("\u03A9");
            TextField tf = new TextField();
            modify(label, tf, ohm, hbox);
            tf.setOnAction(e -> serialResistorTfChanged(tf));
            VBox vbox = (VBox) gridSerial.getChildren().get(0);
            // Find the index of the button in the VBox
            int buttonIndex = vbox.getChildren().indexOf(btnAddResistorSerial);

            // Insert the new HBox before the button
            vbox.getChildren().add(buttonIndex, hbox);
            Resistor resistor = new Resistor(label.getText(), 0);
            circuit.addElements(resistor);
            tf.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                resistor.setResistance(value);
            });
        }
          
    }

    // Remove buttons
    @FXML
    void btnRemoveCapacitorParallelPressed(ActionEvent event) {
        VBox vbox = (VBox) gridParallel.getChildren().get(1);
        int buttonIndex = vbox.getChildren().indexOf(btnAddCapacitorParallel);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countParallel > 0) {
            countParallel--;
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Capacitor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }

    @FXML
    void btnRemoveCapacitorSerialPressed(ActionEvent event) {
        VBox vbox = (VBox) gridSerial.getChildren().get(1);
        int buttonIndex = vbox.getChildren().indexOf(btnAddCapacitorSerial);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countSerial > 0) {
            countSerial--;
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Capacitor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }

    @FXML
    void btnRemoveInductorParallelPressed(ActionEvent event) {
        VBox vbox = (VBox) gridParallel.getChildren().get(2);
        int buttonIndex = vbox.getChildren().indexOf(btnAddInductorParallel);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countParallel > 0) {
            countParallel--;            
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Inductor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }

    @FXML
    void btnRemoveInductorSerialPressed(ActionEvent event) {
        VBox vbox = (VBox) gridSerial.getChildren().get(2);
        int buttonIndex = vbox.getChildren().indexOf(btnAddInductorSerial);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countSerial > 0) {
            countSerial--;
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Inductor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }

    @FXML
    void btnRemoveResistorParallelPressed(ActionEvent event) {
        VBox vbox = (VBox) gridParallel.getChildren().get(0);
        int buttonIndex = vbox.getChildren().indexOf(btnAddResistorParallel);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countParallel > 0) {
            countParallel--;
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Resistor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }

    @FXML
    void btnRemoveResistorSerialPressed(ActionEvent event) {
        VBox vbox = (VBox) gridSerial.getChildren().get(0);
        int buttonIndex = vbox.getChildren().indexOf(btnAddResistorSerial);
        if (vbox.getChildren().get(buttonIndex - 1) instanceof HBox && countSerial > 0) {
            countSerial--;           
            vbox.getChildren().remove(buttonIndex - 1);
            for (int i = circuit.elements.size() -1; i>=0; i--) {
                if (circuit.elements.get(i) instanceof Resistor) {
                    circuit.elements.remove(i);
                    break;
                }
            }
        }
    }
    
    @FXML
    void btnCreateCircuitSerialPressed(ActionEvent event) {
        for (Elements elements : circuit.elements) {
            System.out.println(elements.toString());
        }
        System.out.println(circuit.toString());

        if (circuit.checkShortCircuit()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Short circuit detected.");
            alert.showAndWait();
        } else {
            CircuitDiagram test = new CircuitDiagram(circuit);
            test.show(circuit);
        }

    }

    @FXML
    void btnCreateCircuitParallelPressed(ActionEvent event) {
        for (Elements elements : circuit.elements) {
            System.out.println(elements.toString());
        }
        System.out.println(circuit.toString());

        if (circuit.checkShortCircuit()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING");
            alert.setContentText("Short circuit detected.");
            alert.showAndWait();
        } else {
            CircuitDiagram test = new CircuitDiagram(circuit);
            test.show(circuit);
        }  
    }

    @FXML
    void btnAnalyzeParallelPressed(ActionEvent event) {
        CircuitAnalysis analysis = new CircuitAnalysis();
        analysis.showAnalysis(circuit);
    }

    @FXML
    void btnAnalyzeSerialPressed(ActionEvent event) {
        CircuitAnalysis analysis = new CircuitAnalysis();
        analysis.showAnalysis(circuit);
    }
    
    @FXML
    void initialize() {
        countSerial = 0;
        countParallel = 0;
        
        tfFrequencyParallel.visibleProperty().bind(radioParallelAC.selectedProperty());
        labelFrequencyParallel.visibleProperty().bind(radioParallelAC.selectedProperty());

        tfFrequencySerial.visibleProperty().bind(radioSerialAC.selectedProperty());
        labelFrequencySerial.visibleProperty().bind(radioSerialAC.selectedProperty());  

        tfVoltageParallel.textProperty().addListener((observable, oldValue, newValue) -> {
            if (circuit instanceof ParallelCircuit) {
                double value = Double.parseDouble(newValue);
                source.setVoltage(value);
            }
                
        });

        tfVoltageSerial.textProperty().addListener((observable, oldValue, newValue) -> {
            if (circuit instanceof SerialCircuit) {
                double value = Double.parseDouble(newValue);
                source.setVoltage(value);
            }
                
        });

        tfFrequencyParallel.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                ((ACVoltageSource) source).setFrequency(value);
        });

        tfFrequencySerial.textProperty().addListener((observable, oldValue, newValue) -> {
                double value = Double.parseDouble(newValue);
                ((ACVoltageSource) source).setFrequency(value);
        });

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            for (Elements element:elements) {
                elements.remove(element);
            }
        });

        sourceGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            tfFrequencyParallel.clear();
            tfFrequencySerial.clear();
            tfVoltageParallel.clear();
            tfVoltageSerial.clear();
            if (newValue == radioParallelAC) {
                source = new ACVoltageSource(voltage, frequency);
                circuit = new ParallelCircuit(source);

            } else if (newValue == radioSerialAC) {
                source = new ACVoltageSource(voltage, frequency);
                circuit = new SerialCircuit(source);
            } else if (newValue == radioParallelDC) {
                source = new DCVoltageSource(voltage);
                circuit = new ParallelCircuit(source);
            } else {
                source = new DCVoltageSource(voltage);
                circuit = new SerialCircuit(source);
            }
        });

    }

    void serialInductorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                double doubleValue = Double.parseDouble(newValue);
                if (serialInductorValues.size() > index) {
                    serialInductorValues.set(index, doubleValue);
                } else {
                    serialInductorValues.add(doubleValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    void serialResistorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                double doubleValue = Double.parseDouble(newValue);
                if (serialResistorValues.size() > index) {
                    serialResistorValues.set(index, doubleValue);
                } else {
                    serialResistorValues.add(doubleValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    void parallelCapacitorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                double doubleValue = Double.parseDouble(newValue);
                if (parallelCapacitorValues.size() > index) {
                    parallelCapacitorValues.set(index, doubleValue);
                } else {
                    parallelCapacitorValues.add(doubleValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }
    
    void serialCapacitorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                double doubleValue = Double.parseDouble(newValue);
                    if (serialCapacitorValues.size() > index) {
                        serialCapacitorValues.set(index, doubleValue);
                    } else {
                        serialCapacitorValues.add(doubleValue);
                    }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    void parallelInductorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                Double doubleValue = Double.parseDouble(newValue);
                if (parallelInductorValues.size() > index) {
                        parallelInductorValues.set(index, doubleValue);
                } else {
                        parallelInductorValues.add(doubleValue);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
    }

    void parallelResistorTfChanged(TextField tf) {
        String newValue = tf.getText();
        int index = getTextFieldIndex(tf);
        if (index > -1) {
            try {
                Double doubleValue = Double.parseDouble(newValue);
                if (parallelResistorValues.size() > index) {
                    parallelResistorValues.set(index, doubleValue);
                } else {
                    parallelResistorValues.add(doubleValue);
                }
            } catch (NumberFormatException e) {
                // Handle invalid input
            }
        }
    }


    private int getTextFieldIndex(TextField textField) {
        HBox hbox = (HBox) textField.getParent();
        VBox parentContainer = (VBox) hbox.getParent();
        return parentContainer.getChildren().indexOf(hbox);
    }
    
    void modify(Label label, TextField tf, Label ohm, HBox hbox) {
        ohm.setFont(new Font(16));
        label.setFont(new Font(15));
        
        tf.setPrefWidth(Region.USE_COMPUTED_SIZE);
        tf.setPrefHeight(Region.USE_COMPUTED_SIZE);
        hbox.getChildren().addAll(label, tf, ohm);
        HBox.setMargin(tf, new Insets(0, 0, 0, 5));
        HBox.setMargin(ohm, new Insets(0, 0, 0, 5));
    }
}
