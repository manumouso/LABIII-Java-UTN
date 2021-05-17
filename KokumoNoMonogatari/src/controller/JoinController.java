package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Initializable {

    public Button goBackButton;
    private Scene accessScene;

    public void setAccessScene(Scene scene) {
        accessScene = scene;
    }

    public void openAccessScene(ActionEvent actionEvent) {
        Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(accessScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
