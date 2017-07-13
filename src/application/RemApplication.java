package application;

import database.DatabaseController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class RemApplication extends Application {


    public static void main(String[] args) {
        launch(args);
        DatabaseController.getInstance().disconnect();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            primaryStage.setTitle("Application");
            primaryStage.setScene(createScene());
            primaryStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Creating Scene error");
            alert.setHeaderText("Creating scene failed");
            alert.showAndWait();
            Platform.exit();
        }
    }


    private Scene createScene() {
        Scene scene = null;
        try {
            FXMLLoader loader = new FXMLLoader(RemApplication.class.getResource("/fxml/database.fxml"));
            BorderPane root = loader.load();
            scene = new Scene(root, 1300, 850);
            //scene.getStylesheets().add(Main.class.getResource("RemiStyle.css").toString());

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("File not found");
            alert.setHeaderText("Source fxml file was not found");
            alert.showAndWait();
            Platform.exit();
        }
        return scene;
    }
}
