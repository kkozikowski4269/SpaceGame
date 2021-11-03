package org.example;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.example.controller.GameController;
import org.example.model.Difficulty;
import org.example.model.Game;
import org.example.model.Player;
import org.example.view.GameView;

import java.util.Locale;

public class PrimaryController {

    @FXML
    private TextField nameTextField;
    @FXML
    private ComboBox<Difficulty.Level> difficultyComboBox;
    @FXML
    private Button startButton;
    @FXML
    private Label nameErrorLabel;
    @FXML
    private ComboBox<String> shipStyleComboBox;
    @FXML
    private ComboBox<String> shipColorComboBox;
    @FXML
    private ImageView playerImageView;

    private String shipFile;
    private Player player = Player.getInstance();

    private Scene scene;
    private static Stage stage;

    public void initialize(){
        player.setShipStyle("playerShip1");
        player.setShipColor("blue");
        this.setShipFile(player.getShipStyle(),player.getShipColor());
        this.nameErrorLabel.setVisible(false);
        this.difficultyComboBox.getItems().addAll(Difficulty.Level.Easy,Difficulty.Level.Medium,Difficulty.Level.Hard);
        this.difficultyComboBox.setValue(Difficulty.Level.Medium);

        this.shipStyleComboBox.getItems().addAll("Style 1", "Style 2", "Style 3");
        this.shipStyleComboBox.setValue("Style 1");
        this.shipColorComboBox.getItems().addAll("Blue", "Green", "Orange", "Red");
        this.shipColorComboBox.setValue("Blue");

        this.playerImageView.setImage(new Image(this.shipFile));
    }

    @FXML
    private void startGame(){
        if(this.nameTextField.getText().length() > 0) {
            Game.getInstance().getPlayer().setName(this.nameTextField.getText());
            Game.getInstance().setDifficulty(this.difficultyComboBox.getValue());
            GameView gameView = GameView.getInstance();
            GameController gameController = new GameController();
            this.scene = new Scene(gameView, 1280, 720);
            this.stage = new Stage();
            this.stage.setTitle("Space Game");
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

    @FXML
    public void changeStyle(){
        switch (this.shipStyleComboBox.getValue()){
            case "Style 1":
                this.player.setShipStyle("playerShip1");
                break;
            case "Style 2":
                this.player.setShipStyle("playerShip2");
                break;
            case "Style 3":
                this.player.setShipStyle("playerShip3");
                break;
        }
        this.setShipFile(this.player.getShipStyle(),this.player.getShipColor());
        this.playerImageView.setImage(new Image(this.shipFile));
    }

    @FXML
    public void changeColor(){
        this.player.setShipColor(this.shipColorComboBox.getValue().toLowerCase(Locale.ROOT));
        this.setShipFile(this.player.getShipStyle(),this.player.getShipColor());
        this.playerImageView.setImage(new Image(this.shipFile));
    }

    private void setShipFile(String style, String color){
        this.shipFile = "assets/spaceshooter/PNG/" + style + "_" + color + ".png";
        Game.setPlayerLook(style, color);
    }

    public static Stage getStage(){
        return stage;
    }

}
