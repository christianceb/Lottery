package lottery;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Lottery extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Prevent resize because it looks ugly on windows snap
        primaryStage.resizableProperty().setValue(false);
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Guess the 7 winning numbers");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}