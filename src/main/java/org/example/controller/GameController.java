package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.example.model.*;
import org.example.view.GameView;
import javafx.scene.media.Media;
import java.io.File;

public class GameController {
    private GameView gameView;
    private Game game;
    private Player player;
    private Timeline timeline;
    private boolean paused;

    public GameController(){
        this.gameView = GameView.getInstance();
        this.game = Game.getInstance();
        this.player = this.game.getPlayer();
        //--------NEED TO REMOVE HARDCODING-----------------------
        this.player.setName("Kevin");
        //--------------------------------------------------------
        this.paused = false;
    };

    public void setUp(){
        this.setUpHud(this.game.getHud(), GameView.RIGHT_BOUNDS, 50);
        this.addHud(this.game.getHud(),0,GameView.BOTTOM_BOUNDS-50);
        this.gameView.setBackground(new Image("assets/spaceshooter/Backgrounds/black.png"));
        this.setGameObjectSprite(player,new Image("assets/spaceshooter/PNG/playerShip2_green.png"), 50, 50);
        this.addGameObject(player,GameView.RIGHT_BOUNDS/2, GameView.BOTTOM_BOUNDS-100);
        this.initializeInvaders(30);
    }

    public void run(){
        String file = "src/main/resources/sounds/main_bg_music.mp3";
        // source: https://mixkit.co/free-stock-music/tag/videogame/
        AudioClip bgMusic = this.playSound(file, 0.5, true);
        this.timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if(!this.isPaused()) {
                this.keyActions();
                this.gameActions();
                this.playerAction();
                this.invaderAction();
                this.checkCollisions();
            }else{
                this.keyActions();
            }
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
            String file = "src/main/resources/sounds/player_laser.wav";
            // source: https://mixkit.co/free-sound-effects/game/
            this.playSound(file, 1, false);
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

    public void invaderAction(){
        boolean hasReachedEnd = false;
        for(Invader invader : this.game.getInvaders()){
            invader.move();
            if(invader.getPosX()+invader.getWidth() > GameView.RIGHT_BOUNDS ||
                invader.getPosX() < GameView.LEFT_BOUNDS){
                hasReachedEnd = true;
            }
        }
        if(hasReachedEnd){
            for(Invader invader : this.game.getInvaders()){
                invader.setDirection(invader.getDirection()*-1);
                invader.descend();
            }
        }
    }

    public void gameActions(){

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
                if(this.isPaused()){
                    this.setPaused(false);
                }else{
                    this.setPaused(true);
                }
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
                    String file = "src/main/resources/sounds/invader_hit.wav";
                    // source: https://mixkit.co/free-sound-effects/game/
                    this.playSound(file, 1, false);
                    this.removeGameObject(this.player.getLaser());
                    this.player.destroyLaser();
                    this.removeGameObject(this.game.getInvaders().get(i));
                    this.game.removeInvader(i);
                    this.game.increaseScore(this.game.getInvaderPointValue());
                    this.game.getHud().setScore(this.game.getScore());
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
            invader.setDirection(1);
            invader.setMoveSpeed(0.25);
            invader.updateHitBox();
            x += invader.getWidth()+10;
            if(count%10 == 0){
                x = 10;
                y += invader.getHeight()+10;
            }
            count++;
        }
    }

    public void addHud(HUD hud, double x, double y){
        hud.setLayoutX(x);
        hud.setLayoutY(y);
        this.gameView.getChildren().add(hud);
    }

    public void setUpHud(HUD hud, double width, double height){
        hud.setPrefWidth(width);
        hud.setPrefHeight(height);
        this.game.getHud().addPlayerName(this.player.getName(), 10,20);
        this.game.getHud().addScore(0, 10, 40);
        this.game.getHud().addLevel(1, this.game.getHud().getPrefWidth()/2, 40);
        this.game.getHud().setTextColor(Color.WHITE);
        this.game.getHud().setTextSize(20);
        this.game.getHud().getHealthBar().setImageView(new Image("images/health_bar_bg.png"));
        this.game.getHud().getHealthBar().setWidth(200);
        this.game.getHud().getHealthBar().setHeight(20);
        this.game.getHud().addHealthBar(300, 20);
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

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public AudioClip playSound(String file, double volume, boolean loop){
        if(loop){
            Media media = new Media(new File(file).toURI().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.setVolume(volume);
            audioClip.setCycleCount(AudioClip.INDEFINITE);
            audioClip.play();
            return audioClip;
        }else{
            Media media = new Media(new File(file).toURI().toString());
            AudioClip audioClip = new AudioClip(media.getSource());
            audioClip.setVolume(volume);
            audioClip.play();
            return audioClip;
        }
    }
}
