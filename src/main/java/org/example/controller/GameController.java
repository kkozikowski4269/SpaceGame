package org.example.controller;

import javafx.scene.image.Image;
import org.example.model.Game;
import org.example.model.GameObject;
import org.example.model.Player;
import org.example.view.GameView;

public class GameController {
    private GameView gameView;
    private Game game;
    private Player player;

    public GameController(){
        this.gameView = GameView.getInstance();
        this.game = Game.getInstance();
        this.player = this.game.getPlayer();
    };

    public void setUp(){
        this.setGameObjectSprite(player,new Image("assets/spaceshooter/PNG/playerShip2_green.png"), 50, 50);
        this.addGameObject(player,GameView.RIGHT_BOUNDS/2, GameView.BOTTOM_BOUNDS-100);
    }

    public void addGameObject(GameObject gameObject, double posX, double posY){
        gameObject.setPosX(posX);
        gameObject.setPosY(posY);
        gameView.getChildren().add(gameObject.getImageView());
    }

    public void setGameObjectSprite(GameObject gameObject, Image image, double width, double height){
        gameObject.setImage(image);
        gameObject.setImageView(image);
        gameObject.setWidth(width);
        gameObject.setHeight(height);
    }
}
