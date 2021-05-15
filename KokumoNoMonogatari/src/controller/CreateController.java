package controller;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CreateController {

    public Button goBackButton;
    private Scene accessScene;

    public void setAccessScene(Scene scene) {
        accessScene = scene;
    }

    public void openAccessScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(accessScene);
    }
}
