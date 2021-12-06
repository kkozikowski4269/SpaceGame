package org.example.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import org.example.model.*;
import org.example.view.GameView;

public class GameController {
    private static GameView gameView = GameView.getInstance();
    private static Game game = Game.getInstance();
    private static Player player = Player.getInstance();
    private static Timeline timeline;

    public static void setUp(){
        setUpHud(game.getHud(), GameView.RIGHT_BOUNDS, 50);
        addHud(game.getHud(),0,GameView.BOTTOM_BOUNDS-50);
        gameView.setBackground(new Image(Game.MAIN_BACKGROUND_IMAGE));
        setGameObjectSprite(player,new Image(game.getPlayerShipImage()), 50, 50);
        addGameObject(player,GameView.RIGHT_BOUNDS/2, GameView.BOTTOM_BOUNDS-100);
        initializeInvaders(30);
    }

    public static void run(){
        game.setBgMusic(Sound.playSound(Game.BACKGROUND_SOUND, game.getBgVolume(), true));
        timeline = new Timeline(new KeyFrame(Duration.millis(10), e -> {
            if(!game.isPaused()) {
                keyActions();
                gameActions();
                playerAction();
                invaderAction();
                checkCollisions();
            }else{
                keyActions();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public static void playerAction(){
        // ----------------player moving actions----------------
        if(player.isMovingLeft() && player.getPosX() > GameView.LEFT_BOUNDS){
            player.setDirection(-1);
        }else if(player.isMovingRight() && player.getPosX() < GameView.RIGHT_BOUNDS-player.getWidth()){
            player.setDirection(1);
        }else{
            player.setDirection(0);
        }

        // ----------------player shooting actions----------------
        if(player.isShooting() && !player.hasActiveRocket()){
            gameView.getChildren().add(player.shootLaser().getImageView());
            Sound.playSound(Game.PLAYER_SHOOT_SOUND, 1, false);
        }
        if(player.hasActiveRocket()){
            player.getLaser().move();
            if(player.getLaser().getPosY() < GameView.TOP_BOUNDS){
                removeGameObject(player.getLaser());
                player.destroyLaser();
            }
        }
        player.move();
    }

    public static void invaderAction(){
        boolean hasReachedEnd = false;
        for(Invader invader : game.getInvaders()){
            invader.move();
            if(invader.getPosX()+invader.getWidth() > GameView.RIGHT_BOUNDS ||
                invader.getPosX() < GameView.LEFT_BOUNDS){
                hasReachedEnd = true;
            }
        }
        if(hasReachedEnd){
            for(Invader invader : game.getInvaders()){
                invader.setDirection(invader.getDirection()*-1);
                invader.descend();
            }
        }
    }

    public static void gameActions(){

    }

    public static void keyActions(){
        gameView.getScene().setOnKeyPressed(e->{
            if(e.getCode() == Game.MOVE_LEFT){
                player.setMovingLeft(true);
                if(player.getImageView().getFitWidth() == player.getWidth()) {
                    player.getImageView().setFitWidth(player.getWidth() / 1.35);
                }
            }else if(e.getCode() == Game.MOVE_RIGHT){
                player.setMovingRight(true);
                if(player.getImageView().getFitWidth() == player.getWidth()) {
                    player.getImageView().setFitWidth(player.getWidth() / 1.35);
                }
            }else if(e.getCode() == Game.SHOOT){
                player.setShooting(true);
            }else if(e.getCode() == Game.PAUSE){
                if(!PauseMenuController.isOpen()) {
                    game.getBgMusic().stop();
                    game.pause(true);
                    PauseMenuController.set();
                    PauseMenuController.getStage().show();
                }
            }
        });
        gameView.getScene().setOnKeyReleased(e->{
            if(e.getCode() == Game.MOVE_LEFT){
                player.setMovingLeft(false);
                player.getImageView().setFitWidth(player.getWidth());
            }else if(e.getCode() == Game.MOVE_RIGHT){
                player.setMovingRight(false);
                player.getImageView().setFitWidth(player.getWidth());
            }else if(e.getCode() == Game.SHOOT){
                player.setShooting(false);
            }else if(e.getCode() == Game.PAUSE){

            }
        });
    }

    public static void checkCollisions(){
        if(player.hasActiveRocket()){
            int i = 0;
            while(i < game.getInvaders().size() && player.hasActiveRocket()){
                if (game.getInvaders().get(i).isHitBy(player.getLaser())) {
                    Sound.playSound(Game.INVADER_HIT_SOUND, 1, false);
                    removeGameObject(player.getLaser());
                    player.destroyLaser();
                    removeGameObject(game.getInvaders().get(i));
                    game.removeInvader(i);
                    game.increaseScore(game.getInvaderPointValue());
                    game.getHud().setScore(game.getScore());
                }
                i++;
            }
        }
    }

    // NEED TO REMOVE HARDCODING FROM THIS FUNCTION
    public static void initializeInvaders(int numInvaders){
        for(int i = 0; i < numInvaders; i++){
            game.addInvader(new Invader());
        }
        double x = 10;
        double y = 10;
        int count = 1;
        for(Invader invader : game.getInvaders()){
            setGameObjectSprite(invader, new Image(Game.ENEMY_SHIP_IMAGE), 50, 50);
            addGameObject(invader, x, y);
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

    public static void addHud(HUD hud, double x, double y){
        hud.setLayoutX(x);
        hud.setLayoutY(y);
        gameView.getChildren().add(hud);
    }

    public static void setUpHud(HUD hud, double width, double height){
        hud.setPrefWidth(width);
        hud.setPrefHeight(height);
        game.getHud().addPlayerName(player.getName(), 10,20);
        game.getHud().addScore(0, 10, 40);
        game.getHud().addLevel(1, game.getHud().getPrefWidth()/2, 40);
        game.getHud().setTextColor(Color.WHITE);
        game.getHud().setTextSize(20);
        game.getHud().getHealthBar().setMaxSize(200, 20);
        game.getHud().addHealthBar(300, 20);
    }

    public static void addGameObject(GameObject gameObject, double posX, double posY){
        gameObject.setPosX(posX);
        gameObject.setPosY(posY);
        gameView.getChildren().add(gameObject.getImageView());
    }

    public static void removeGameObject(GameObject gameObject){
        gameView.getChildren().remove(gameObject.getImageView());
    }

    public static void setGameObjectSprite(GameObject gameObject, Image image, double width, double height){
        gameObject.setImage(image);
        gameObject.setImageView(image);
        gameObject.setWidth(width);
        gameObject.setHeight(height);
    }
}
