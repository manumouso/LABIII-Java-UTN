package view;

import controller.AccessController;
import controller.CreateController;
import controller.JoinController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class Main extends Application {

    static Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception {
        window=primaryStage;
        // getting loader and a pane for the first scene.
        // loader will then give a possibility to get related controller
        FXMLLoader accessPageLoader = new FXMLLoader(getClass().getResource("Access.fxml"));
        Parent accessPane = accessPageLoader.load();
        Scene accessScene = new Scene(accessPane, 550, 350);

        // getting loader and a pane for the second scene
        FXMLLoader createPageLoader = new FXMLLoader(getClass().getResource("Create.fxml"));
        Parent createPane = createPageLoader.load();
        Scene createScene = new Scene(createPane, 550, 350);

        //getting loader and a pane for the third scene
        FXMLLoader joinPageLoader = new FXMLLoader(getClass().getResource("Join.fxml"));
        Parent joinPane = joinPageLoader.load();
        Scene joinScene= new Scene(joinPane, 550, 350);

        // injecting second scene into the controller of the first scene
        AccessController firstPaneController = (AccessController) accessPageLoader.getController();
        firstPaneController.setCreateScene(createScene);

        //injecting the third scene into the controller of the first scene
        firstPaneController.setJoinScene(joinScene);

        // injecting first scene into the controller of the second scene
        CreateController secondPaneController = (CreateController) createPageLoader.getController();
        secondPaneController.setAccessScene(accessScene);

        //injecting first scene into the controller of the third scene
        JoinController thirdPaneController= (JoinController) joinPageLoader.getController();
        thirdPaneController.setAccessScene(accessScene);

        window.setTitle("Kokumo No Monogatari");
        window.getIcons().add(new Image("resource/image/kokumo.jpg"));
        window.setOnCloseRequest(e->{
            e.consume();
            quitGame();
        });
        window.setScene(accessScene);
        window.setResizable(false);
        window.show();
    }

    public static void quitGame(){
        window.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
