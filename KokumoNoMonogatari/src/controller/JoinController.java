package controller;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class JoinController implements Initializable {

    public Button goBackButton,joinButton;
    private Scene accessScene;
    public TextArea messagesTxtArea;
    private Service<Void> backgroundThread;

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

    public void clickedJoinServer(ActionEvent event){
        backgroundThread= new Service<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() throws Exception {
                        //Here call clientController
                        updateMessage("Connection established, Client Ready ");
                        return null;
                    }
                };
            }
        };
        backgroundThread.setOnSucceeded(workerStateEvent -> {
            System.out.println("Connection ended successfully");
            //unbind to prevent Exceptions, next time the textArea has some message appended main thread
            messagesTxtArea.textProperty().unbind();
        });
        backgroundThread.setOnFailed(workerStateEvent -> {
            System.out.println("Connection lost");
            messagesTxtArea.textProperty().unbind();
        });
        //the text area from the GUI thread/ main thread is bound to the updateMessage
        messagesTxtArea.textProperty().bind(backgroundThread.messageProperty());
        //this function starts the thread
        backgroundThread.restart();
    }
}
