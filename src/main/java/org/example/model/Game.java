package org.example.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Invader> invaders;
    private int score;
    private int invaderPointValue;
    private static Game game = new Game();

    //CONTROLS
    public static final KeyCode MOVE_LEFT = KeyCode.A;
    public static final KeyCode MOVE_RIGHT = KeyCode.D;
    public static final KeyCode SHOOT = KeyCode.SPACE;
    public static final KeyCode PAUSE = KeyCode.ESCAPE;

    private Game(){
        player = Player.getInstance();
        this.invaders = new ArrayList<>();
        this.invaderPointValue = 150;
        this.score = 0;
    };

    public static Game getInstance(){
        return Game.game;
    }

    public Player getPlayer(){
        return this.player;
    }

    public ArrayList<Invader> getInvaders() {
        return invaders;
    }

    public void addInvader(Invader invader){
        this.invaders.add(invader);
    }

    public void removeInvader(int position){
        this.invaders.remove(position);
    }

    public void removeInvader(Invader invader){
        this.invaders.remove(invader);
    }

    public int numInvaders(){
        return this.invaders.size();
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increment){
        this.score += increment;
    }
}
