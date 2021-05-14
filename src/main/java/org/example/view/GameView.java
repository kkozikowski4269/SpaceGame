package org.example.view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class GameView extends Pane {

    private static Scene scene;
    private static GameView gameView = new GameView();
    public static final double LEFT_BOUNDS = 0;
    public static final double RIGHT_BOUNDS = 1280;
    public static final double TOP_BOUNDS = 0;
    public static final double BOTTOM_BOUNDS = 720;

    private GameView(){
        this.setBackground();
    }

    public static GameView getInstance(){
        return gameView;
    }

    public void setBackground(){
        BackgroundImage bgImage = new BackgroundImage(new Image("assets/spaceshooter/Backgrounds/black.png"),
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(this.getWidth(), this.getHeight(), false, false, true, true));
        Background bg = new Background(bgImage);
        this.setBackground(bg);
    }
}
