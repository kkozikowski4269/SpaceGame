package org.example.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import org.example.model.Game;
import org.example.model.GameObject;
import org.example.model.Player;
import org.example.view.GameView;

public class GameController {
    private GameView gameView;
    private Game game;
    private Player player;
    private Timeline timeline;

    public GameController(){
        this.gameView = GameView.getInstance();
        this.game = Game.getInstance();
        this.player = this.game.getPlayer();
    };

    public void setUp(){
        this.setGameObjectSprite(player,new Image("assets/spaceshooter/PNG/playerShip2_green.png"), 50, 50);
        this.addGameObject(player,GameView.RIGHT_BOUNDS/2, GameView.BOTTOM_BOUNDS-100);
    }

    public void run(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> {
            this.playerAction();
        }));
        this.timeline.play();
    }

    public void playerAction(){

        this.gameView.getScene().setOnKeyPressed(e->{
            boolean keyRight = false;
            boolean keyLeft = false;
            boolean keyShoot = false;
            boolean keyPause = false;
            if(e.getCode() == Game.MOVE_LEFT){
                this.player.setPosX(this.player.getPosX()-5);
            }else if(e.getCode() == Game.MOVE_RIGHT){
                this.player.setPosX(this.player.getPosX()+5);
            }else if(e.getCode() == Game.SHOOT){
                System.out.println("Shoot");
            }else if(e.getCode() == Game.PAUSE){
                System.out.println("Pause");
            }
        });
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
