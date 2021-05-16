package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import org.example.model.Game;
import org.example.model.GameObject;
import org.example.model.Invader;
import org.example.model.Player;
import org.example.view.GameView;

import java.util.ArrayList;

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
        this.initializeInvaders(30);
    }

    public void run(){
        this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            this.keyActions();
            this.playerAction();
            this.checkCollisions();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        this.timeline.play();
    }

    public void playerAction(){
        // ----------------player moving actions----------------
        if(this.player.isMovingLeft() && this.player.getPosX() > GameView.LEFT_BOUNDS){
            this.player.setDirection(-1);
        }else if(this.player.isMovingRight() && this.player.getPosX() < GameView.RIGHT_BOUNDS-this.player.getWidth()){
            this.player.setDirection(1);
        }else{
            this.player.setDirection(0);
        }

        // ----------------player shooting actions----------------
        if(this.player.isShooting() && !this.player.hasActiveRocket()){
            this.gameView.getChildren().add(this.player.shootLaser().getImageView());
        }
        if(this.player.hasActiveRocket()){
            this.player.getLaser().move();
            if(this.player.getLaser().getPosY() < GameView.TOP_BOUNDS){
                this.removeGameObject(this.player.getLaser());
                this.player.destroyLaser();
            }
        }
        this.player.move();
    }

    public void keyActions(){
        this.gameView.getScene().setOnKeyPressed(e->{
            if(e.getCode() == Game.MOVE_LEFT){
                this.player.setMovingLeft(true);
            }else if(e.getCode() == Game.MOVE_RIGHT){
                this.player.setMovingRight(true);
            }else if(e.getCode() == Game.SHOOT){
                this.player.setShooting(true);
            }else if(e.getCode() == Game.PAUSE){

            }
        });
        this.gameView.getScene().setOnKeyReleased(e->{
            if(e.getCode() == Game.MOVE_LEFT){
                this.player.setMovingLeft(false);
            }else if(e.getCode() == Game.MOVE_RIGHT){
                this.player.setMovingRight(false);
            }else if(e.getCode() == Game.SHOOT){
                this.player.setShooting(false);
            }else if(e.getCode() == Game.PAUSE){

            }
        });
    }

    public void checkCollisions(){
        if(this.player.hasActiveRocket()){
            int i = 0;
            while(i < this.game.getInvaders().size() && this.player.hasActiveRocket()){
                if (this.game.getInvaders().get(i).isHitBy(this.player.getLaser())) {
                    this.removeGameObject(this.player.getLaser());
                    this.player.destroyLaser();
                    this.removeGameObject(this.game.getInvaders().get(i));
                    this.game.removeInvader(i);
                }
                i++;
            }
        }
    }

    // NEED TO REMOVE HARDCODING FROM THIS FUNCTION
    public void initializeInvaders(int numInvaders){
        for(int i = 0; i < numInvaders; i++){
            this.game.addInvader(new Invader());
        }
        double x = 10;
        double y = 10;
        int count = 1;
        for(Invader invader : this.game.getInvaders()){
            this.setGameObjectSprite(invader, new Image("assets/spaceshooter/PNG/Enemies/enemyRed1.png"), 50, 50);
            this.addGameObject(invader, x, y);
            invader.updateHitBox();
            x += invader.getWidth()+10;
            if(count%10 == 0){
                x = 10;
                y += invader.getHeight()+10;
            }
            count++;
        }
    }

    public void addGameObject(GameObject gameObject, double posX, double posY){
        gameObject.setPosX(posX);
        gameObject.setPosY(posY);
        gameView.getChildren().add(gameObject.getImageView());
    }

    public void removeGameObject(GameObject gameObject){
        this.gameView.getChildren().remove(gameObject.getImageView());
    }

    public void setGameObjectSprite(GameObject gameObject, Image image, double width, double height){
        gameObject.setImage(image);
        gameObject.setImageView(image);
        gameObject.setWidth(width);
        gameObject.setHeight(height);
    }
}
