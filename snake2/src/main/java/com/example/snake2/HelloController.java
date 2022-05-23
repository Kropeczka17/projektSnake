package com.example.snake2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private Button howToPlayButton;

    @FXML
    private Button backFromHtpToHelloView;

    @FXML
    private Button authorButton;

    @FXML
    private Button backFromAuthorsToHelloView;

    @FXML
    private Button gameModesButton;

    @FXML
    private Button backFromGameModesToHelloView;

    @FXML
    private Button turnOnClassicModeButton;
    @FXML
    private Button backFromClassicModeToGameModes;

    @FXML
    private void fromHelloToHowToPlay (ActionEvent event) throws Exception {
        Stage stage;
        Parent root;

        if(event.getSource()==howToPlayButton){
            stage = (Stage) howToPlayButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("how-to-play-view.fxml"));
        }
        else{
            stage = (Stage) backFromHtpToHelloView.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void fromHelloToAuthors(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if(event.getSource()==authorButton){
            stage = (Stage) authorButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("authors-view.fxml"));
        }
        else{
            stage = (Stage) backFromAuthorsToHelloView.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void fromHelloToGameModes(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if(event.getSource()==gameModesButton){
            stage = (Stage) gameModesButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("game-modes-view.fxml"));
        }
        else{
            stage = (Stage) backFromGameModesToHelloView.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public static Dir direction = Dir.left;

    public enum Dir {
        left, right, up, down
    }

    @FXML
    public void fromModesToClassic(ActionEvent event) throws IOException {
        Stage stage;
        Parent root;

        if(event.getSource()==turnOnClassicModeButton){
            stage = (Stage) turnOnClassicModeButton.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("classic-game.fxml"));
        }
        else{
            stage = (Stage) backFromClassicModeToGameModes.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("game-modes-view.fxml"));
        }
        Scene scene = new Scene(root);
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            if (key.getCode() == KeyCode.W) {
                System.out.println("W");
                direction = Dir.up;
            }
            if (key.getCode() == KeyCode.A) {
                direction = Dir.left;
            }
            if (key.getCode() == KeyCode.S) {
                direction = Dir.down;
            }
            if (key.getCode() == KeyCode.D) {
                direction = Dir.right;
            }

        });
        stage.setScene(scene);
        stage.show();
    }

    //--------------------------------------------------------------------
    @FXML
    private Button classicModeStartButton;

    @FXML
    private VBox classicGameVBox;

    @FXML
    private Pane classicModePane;

    ClassicMode classicMode = new ClassicMode();

    public void startClassicGame() throws IOException, InterruptedException {
        classicMode.startGame(classicModeStartButton, backFromClassicModeToGameModes, classicModePane, classicGameVBox);
    }
}