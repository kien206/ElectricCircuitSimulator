
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GUI.fxml"));
        Pane root = loader.load();

        // Create the scene
        Scene scene = new Scene(root, 300, 300);

        // Set the scene on the primary stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circuit Simulator");
        primaryStage.show();
    }
}
