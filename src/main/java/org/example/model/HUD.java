package org.example.model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HUD extends Pane {
    private Text playerName;
    private Text score;
    private Text level;

    public HUD(){
        this.playerName = new Text("");
        this.score = new Text("");
        this.level = new Text("");
    }

    public Text getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName.setText(playerName);
    }

    public void addPlayerName(String playerName, double x, double y){
        this.playerName.setText(playerName);
        this.playerName.setLayoutX(x);
        this.playerName.setLayoutY(y);
        this.getChildren().add(this.playerName);
    }

    public Text getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score.setText("Score: "+score);
    }

    public void addScore(int score, double x, double y){
        this.score.setText("Score: "+score);
        this.score.setLayoutX(x);
        this.score.setLayoutY(y);
        this.getChildren().add(this.score);
    }

    public Text getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level.setText("Level: "+level);
    }

    public void addLevel(int level, double x, double y){
        this.level.setText("Level: "+level);
        this.level.setLayoutX(x);
        this.level.setLayoutY(y);
        this.getChildren().add(this.level);
    }

    public void setTextColor(Color color){
        this.playerName.setFill(color);
        this.score.setFill(color);
        this.level.setFill(color);
    }

    public void setTextSize(double size){
        this.playerName.setFont(Font.font(size));
        this.score.setFont(Font.font(size));
        this.level.setFont(Font.font(size));
    }
}
