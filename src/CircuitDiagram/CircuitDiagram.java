package CircuitDiagram;

import Circuit.ElectricalCircuit;
import Circuit.ParallelCircuit;
import Circuit.SerialCircuit;
import Elements.Capacitor;
import Elements.Inductor;
import Elements.Resistor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CircuitDiagram {

    private ElectricalCircuit circuit;

    public void show(ElectricalCircuit circuit) {
        Stage primaryStage = new Stage();
        int a = this.circuit.elements.size();
        String[] components = new String[a];
        for (int i=0; i< a; i++) {
            components[i] = String.valueOf(this.circuit.elements.get(i).getName().charAt(0));
        }
        String circuitType = "";
        if (this.circuit instanceof SerialCircuit) {
            circuitType = "Serial";
        } else if (this.circuit instanceof ParallelCircuit) {
            circuitType = "Parallel";
        }
        
        Pane circuitPane = new Pane();

        Image image;
        ImageView imageView;
        Line wire;
        Button button;
        
        if (circuitType == "Serial") {
            double x = 150;
            double y = 350;

            wire = new Line(x-50, y, x, y);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            for(byte i = 0; i < a; i++) {
                switch(components[i]) {
                    case "R":
                    Resistor element1 = (Resistor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("resistor.png"));
                    imageView = new ImageView(image);
                    x -= image.getHeight()/12;
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y-image.getHeight()/2);

                    Label label1 = new Label();
                    label1.setVisible(false);
                    label1.setText(String.valueOf(element1.getCurrentIntensity(circuit)) + "I\n" + String.valueOf(element1.getVoltage(circuit)) + "U");
                    label1.setFont(Font.font(15));
                    label1.setLayoutX(x+image.getWidth()*5/12);
                    label1.setLayoutY(y-image.getHeight()*1/2);

                    button = new Button();
                    button.setVisible(true);
                    button.setStyle("-fx-background-color: transparent;");
                    button.setLayoutX(x);
                    button.setLayoutY(y-image.getHeight()*1/4);
                    button.setMaxWidth(image.getWidth());
                    button.setMaxHeight(image.getHeight()/2);
                    button.setMinWidth(image.getWidth());
                    button.setMinHeight(image.getHeight()/2);
                    button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> label1.setVisible(!label1.isVisible()));

                    circuitPane.getChildren().addAll(imageView, label1, button);
                    x += image.getWidth()*11/12;   

                    Text resistorLabel = new Text(x-image.getWidth()*2/3, y+50, "R" + (i+1) + ": " + String.valueOf(element1.getResistance()) + "Ω");
                    resistorLabel.setFont(Font.font(13));
                    circuitPane.getChildren().add(resistorLabel);
                    break; 

                    case "L":
                    Inductor element2 = (Inductor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("inductor.png"));
                    imageView = new ImageView(image);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y-image.getHeight()/2);

                    Label label2 = new Label();
                    label2.setVisible(false);
                    label2.setText(element2.getCurrentIntensity(circuit) + "I\n" + String.valueOf(element2.getVoltage(circuit)) + "U");
                    label2.setFont(Font.font(15));
                    label2.setLayoutX(x+image.getWidth()*5/12);
                    label2.setLayoutY(y-image.getHeight()*5/3);

                    button = new Button();
                    button.setVisible(true);
                    button.setStyle("-fx-background-color: transparent;");
                    button.setLayoutX(x);
                    button.setLayoutY(y-image.getHeight()*3/4);
                    button.setMaxWidth(image.getWidth());
                    button.setMaxHeight(image.getHeight()*3/2);
                    button.setMinWidth(image.getWidth());
                    button.setMinHeight(image.getHeight()*3/2);
                    button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> label2.setVisible(!label2.isVisible()));

                    circuitPane.getChildren().addAll(imageView, label2, button);
                    x += image.getWidth();

                    Text inductorLabel = new Text(x-image.getWidth()*3/4, y+50, "L" + (i+1) + ": " + String.valueOf(element2.getInductance()*1e6) + "\u03BCH");
                    inductorLabel.setFont(Font.font(13));
                    circuitPane.getChildren().add(inductorLabel);
                    break;

                    case "C":
                    Capacitor element3 = (Capacitor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("capacitor.png"));
                    imageView = new ImageView(image);
                    imageView.setLayoutX(x);
                    imageView.setLayoutY(y-image.getHeight()/2);

                    Label label3 = new Label();
                    label3.setVisible(false);
                    label3.setText(element3.getCurrentIntensity(circuit) + "I\n" + String.valueOf(element3.getVoltage(circuit)) + "U");
                    label3.setFont(Font.font(15));
                    label3.setLayoutX(x+image.getWidth()*5/12);
                    label3.setLayoutY(y-image.getHeight()*13/12);

                    button = new Button();
                    button.setVisible(true);
                    button.setStyle("-fx-background-color: transparent;");
                    button.setLayoutX(x);
                    button.setLayoutY(y-image.getHeight()/2);
                    button.setMaxWidth(image.getWidth());
                    button.setMaxHeight(image.getHeight());
                    button.setMinWidth(image.getWidth());
                    button.setMinHeight(image.getHeight());
                    button.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> label3.setVisible(!label3.isVisible()));

                    circuitPane.getChildren().addAll(imageView, label3, button);
                    x += image.getWidth();

                    Text capacitorLabel = new Text(x-image.getWidth()*5/6, y+50, "C" + (i+1) + ": " + String.valueOf(element3.getCapacitance()*1e6) + "\u03BCF");
                    capacitorLabel.setFont(Font.font(13));
                    circuitPane.getChildren().add(capacitorLabel);
                    break;
                }
                
                if (i == a-1) {
                    wire = new Line(x, y, x+50, y);
                    x += 50;
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);
                }

                else {
                    wire = new Line(x, y, x+25, y);
                    x += 25;
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);
                }
            }

            wire = new Line(100, y, 100, y+200);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            wire = new Line(x, y, x, y+200);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            y += 200;

            image = new Image(getClass().getResourceAsStream("power.png"));
            imageView = new ImageView(image);
            imageView.setRotate(90);

            wire = new Line(100, y, 100+(x-image.getHeight()-100)/2+image.getHeight()/12, y);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            imageView.setLayoutX(100+(x-image.getHeight()-100)/2);
            imageView.setLayoutY(y-image.getHeight()/2);
            circuitPane.getChildren().add(imageView);

            Text voltageLabel = new Text(100+(x-image.getHeight()-100)/2+image.getHeight()*2/5, y+75, String.valueOf(this.circuit.source.getVoltage()) + "V");
            voltageLabel.setFont(Font.font(13));
            circuitPane.getChildren().add(voltageLabel);

            wire = new Line(100+(x-image.getHeight()-100)/2+image.getHeight()*11/12, y, x, y);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

        }

        else if (circuitType == "Parallel"){

            double x = 150;
            double y = 350;

            wire = new Line(x, y, x, y+68);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            wire = new Line(x, y, x + 125*a, y);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            y += 68;

            image = new Image(getClass().getResourceAsStream("power.png"));
            imageView = new ImageView(image);
            imageView.setLayoutX(x-image.getWidth()/2);
            imageView.setLayoutY(y-image.getHeight()/12);
            circuitPane.getChildren().add(imageView);

            y += image.getHeight()*5/6;

            Text voltageLabel = new Text(100+(x-image.getWidth()-150)/3, y-image.getHeight()/3, String.valueOf(this.circuit.source.getVoltage()) + "V");
            voltageLabel.setFont(Font.font(13));
            circuitPane.getChildren().add(voltageLabel);

            wire = new Line(x, y, x, y+68);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            y += 68;

            wire = new Line(x, y, x + 125*a, y);
            wire.setStrokeWidth(5);
            circuitPane.getChildren().add(wire);

            x += 125*a;

            for(int i = a-1; i >= 0; i--) {

                switch(components[i]) {
                    case "R":
                    Resistor element1 = (Resistor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("resistor.png"));
                    imageView = new ImageView(image);
                    imageView.setRotate(90);
                    imageView.setLayoutX(x-image.getHeight()/2);
                    imageView.setLayoutY(350+(y-350-image.getWidth())/2);
                    circuitPane.getChildren().add(imageView);

                    Text resistorLabel1 = new Text(x-image.getHeight()/2, y-image.getWidth(), "R" + (i+1));
                    Text resistorLabel2 = new Text(x-image.getHeight()/2, y-image.getWidth()*5/6, String.valueOf(element1.getResistance()) + "Ω");
                    resistorLabel1.setFont(Font.font(13));
                    resistorLabel2.setFont(Font.font(13));
                    circuitPane.getChildren().add(resistorLabel1);
                    circuitPane.getChildren().add(resistorLabel2);

                    break; 
                    case "L":
                    Inductor element2 = (Inductor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("inductor.png"));
                    imageView = new ImageView(image);
                    imageView.setRotate(90);
                    imageView.setLayoutX(x-image.getHeight()*33/24);
                    imageView.setLayoutY(350+(y-350)/2-image.getWidth()/6);
                    circuitPane.getChildren().add(imageView);

                    Text inductorLabel1 = new Text(x-image.getHeight()*2, y-image.getWidth()*7/6, "L" + (i+1));
                    Text inductorLabel2 = new Text(x-image.getHeight()*2, y-image.getWidth()*23/24, String.valueOf(element2.getInductance()*1e6) + "\u03BCH");
                    inductorLabel1.setFont(Font.font(13));
                    inductorLabel2.setFont(Font.font(13));
                    circuitPane.getChildren().add(inductorLabel1);
                    circuitPane.getChildren().add(inductorLabel2);

                    break;
                    case "C":
                    Capacitor element3 = (Capacitor) this.circuit.elements.get(i);
                    image = new Image(getClass().getResourceAsStream("capacitor.png"));
                    imageView = new ImageView(image);
                    imageView.setRotate(90);
                    imageView.setLayoutX(x-image.getHeight()/2);
                    imageView.setLayoutY(350+(y-350-image.getWidth())/2);
                    circuitPane.getChildren().add(imageView);

                    Text capacitorLabel1 = new Text(x-image.getHeight()*1.25, y-image.getWidth()*2, "C" + (i+1));
                    Text capacitorLabel2 = new Text(x-image.getHeight()*1.25, y-image.getWidth()*5/3, String.valueOf(element3.getCapacitance()*1e6) + "\u03BCF");
                    capacitorLabel1.setFont(Font.font(13));
                    capacitorLabel2.setFont(Font.font(13));
                    circuitPane.getChildren().add(capacitorLabel1);
                    circuitPane.getChildren().add(capacitorLabel2);

                    break;
                }

                if (components[i] == "R") {
                    wire = new Line(x, 350, x, 350+(y-350-image.getWidth())/2+image.getWidth()/12);
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);

                    wire = new Line(x, y, x, 350+(y-350+image.getWidth())/2-image.getWidth()/12);
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);
                    x -= 125;
                }

                else {
                    wire = new Line(x, 350, x, 350+(y-350-image.getWidth())/2);
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);

                    wire = new Line(x, y, x, 350+(y-350+image.getWidth())/2);
                    wire.setStrokeWidth(5);
                    circuitPane.getChildren().add(wire);
                    x -= 125;
                }

            }

        }

        // Create the scene and add the circuit pane
        Scene scene = new Scene(circuitPane, 1080, 720);

        // Set up the stage
        primaryStage.setTitle("Circuit Diagram");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public CircuitDiagram(ElectricalCircuit circuit) {
        this.circuit = circuit;
    }
    

}
