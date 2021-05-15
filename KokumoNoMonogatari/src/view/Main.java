package view;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;
    Scene entry,createGame,joinGame,gameView;
    @Override
    public void start(Stage primaryStage) throws Exception{

        window=primaryStage;
        window.setTitle("Kokumo No Monogatari");
        window.getIcons().add(new Image("resource/image/kokumo.jpg"));
        window.setOnCloseRequest(e->{
            e.consume();
            quitGame();
        });

        Label welcome= new Label("Welcome Ninja");
        Button createButton= new Button();
        createButton.setText("Create Game");
        //createButton.setOnAction(e->window.setScene(createGame));

        //principal
        Button joinButton= new Button();
        joinButton.setText("Join Game");
        joinButton.setOnAction(e->window.setScene(joinGame));
        Button quitButton= new Button();
        quitButton.setText("Quit");
        quitButton.setOnAction(e->quitGame());

        //joinGame
        Label ipLabel = new Label("Enter IP adress");
        TextField ipInput = new TextField();
        ipInput.setPromptText("123.12.10.23.200.20");
        ipInput.setMaxWidth(145);
        Button sendButton = new Button();
        sendButton.setText("Send");
        Button returnButton = new Button();
        returnButton.setText("Go Back");
        returnButton.setOnAction(e->window.setScene(entry));

        //principal
        VBox layout = new VBox(20);
        layout.getChildren().addAll(welcome,createButton,joinButton,quitButton);
        layout.setAlignment(Pos.CENTER);

        //joinGame
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10,10,10,10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        GridPane.setConstraints(ipLabel,0,0);
        GridPane.setConstraints(ipInput,1,0);
        GridPane.setConstraints(sendButton,1,1);
        GridPane.setConstraints(returnButton,0,1);

        gridPane.getChildren().addAll(ipLabel,ipInput,sendButton,returnButton);
        gridPane.setAlignment(Pos.CENTER);


        BorderPane borderPane =new BorderPane();
        borderPane.setCenter(gridPane);



        joinGame = new Scene(borderPane,470,450);

        entry= new Scene(layout,470,450);
        window.setScene(entry);
        window.setResizable(false);
        window.show();
    }

    private void quitGame(){
        window.close();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
