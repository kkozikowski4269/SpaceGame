package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.model.Difficulty;
import org.example.model.Game;
import org.example.view.GameView;

public class PrimaryController {

    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<Difficulty.Level> difficultyComboBox;
    @FXML
    private Button startButton;
    @FXML
    private Label nameErrorLabel;

    public void initialize(){
        this.nameErrorLabel.setVisible(false);
        this.difficultyComboBox.getItems().add(Difficulty.Level.Easy);
        this.difficultyComboBox.getItems().add(Difficulty.Level.Medium);
        this.difficultyComboBox.getItems().add(Difficulty.Level.Hard);
        this.difficultyComboBox.setValue(Difficulty.Level.Medium);
    }

    @FXML
    private void startGame(){
        if(this.nameTextField.getText().length() > 0) {
            Game.getInstance().getPlayer().setName(this.nameTextField.getText());
            Game.getInstance().setDifficulty(this.difficultyComboBox.getValue());
            GameView gameView = GameView.getInstance();
            GameController gameController = new GameController();
            Scene scene = new Scene(gameView, 1280, 720);
            Stage stage = new Stage();
            stage.centerOnScreen();
            stage.setScene(scene);
            scene.setRoot(gameView);
            stage.show();
            gameController.setUp();
            gameController.run();
            App.getStage().close();
        }else{
            this.nameErrorLabel.setVisible(true);
        }
    }


}
