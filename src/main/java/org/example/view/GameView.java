package org.example.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import org.example.model.HUD;

public class GameView extends Pane {

    private static Scene scene;
    private static GameView gameView = new GameView();
    public static final double LEFT_BOUNDS = 0;
    public static final double RIGHT_BOUNDS = 1280;
    public static final double TOP_BOUNDS = 0;
    public static final double BOTTOM_BOUNDS = 720;
    private Background bg;

    private GameView(){}

    public static GameView getInstance(){
        return gameView;
    }

    public void setBackground(Image image){
        BackgroundImage bgImage = new BackgroundImage(
            image,
            BackgroundRepeat.REPEAT,
            BackgroundRepeat.REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(this.getWidth(), this.getHeight(), false, false, true, true)
        );
        this.bg = new Background(bgImage);
        this.setBackground(bg);
    }
}
