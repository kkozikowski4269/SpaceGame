package org.example.model;

import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Invader> invaders;
    private int score;
    private int invaderPointValue;
    private static Game game = new Game();

    private Game(){
        player = Player.getInstance();
        this.invaders = new ArrayList<>();
        this.invaderPointValue = 150;
        this.score = 0;
    };

    public void movePlayer(){

    }

    public static Game getInstance(){
        return Game.game;
    }

    public Player getPlayer(){
        return this.player;
    }

    public void addInvader(Invader invader){
        this.invaders.add(invader);
    }

    public void removeInvader(int position){
        this.invaders.remove(position);
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
