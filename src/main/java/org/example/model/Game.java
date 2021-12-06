package org.example.model;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;

import java.util.ArrayList;

public class Game {
    private Player player;
    private ArrayList<Invader> invaders;
    private int score;
    private int invaderPointValue;
    private HUD hud;
    private Difficulty difficulty;
    private boolean paused;
    private static Game game = new Game();
    private static String playerStyle;
    private static String playerColor;
    private static String playerShipImage;
    private AudioClip bgMusic;
    private AudioClip introMusic;
    private double bgVolume;

    //------------Game Constants-------------
    // image files
    public static final String PLAYER_LASER_IMAGE = "assets/spaceshooter/PNG/Lasers/laserGreen04.png";
    public static final String PLAYER_HEALTHBAR_IMAGE = "images/health_bar_bg.png";
    public static final String ENEMY_SHIP_IMAGE = "assets/spaceshooter/PNG/Enemies/enemyRed1.png";
    public static final String MAIN_BACKGROUND_IMAGE = "assets/spaceshooter/Backgrounds/black.png";

    // sound files
    // source: https://mixkit.co/free-stock-music/tag/videogame/
    // source: http://soundimage.org/wp-content/uploads/2016/06/And-the-Machines-Came-at-Midnight.mp3
    // source: http://soundimage.org/wp-content/uploads/2016/11/Automation.mp3
//    public static final String BACKGROUND_SOUND = "src/main/resources/sounds/main_bg_music.mp3";
    public static final String BACKGROUND_SOUND = "src/main/resources/sounds/And-the-Machines-Came-at-Midnight.mp3";
    public static final String INTRO_SOUND = "src/main/resources/sounds/Automation.mp3";
    // source: https://mixkit.co/free-sound-effects/game/
    public static final String PLAYER_SHOOT_SOUND = "src/main/resources/sounds/player_laser.wav";
    // source: https://mixkit.co/free-sound-effects/game/
    public static final String INVADER_HIT_SOUND = "src/main/resources/sounds/invader_hit.wav";


    //-------------CONTROLS------------------
    public static final KeyCode MOVE_LEFT = KeyCode.A;
    public static final KeyCode MOVE_RIGHT = KeyCode.D;
    public static final KeyCode SHOOT = KeyCode.SPACE;
    public static final KeyCode PAUSE = KeyCode.ESCAPE;

    private Game(){
        this.player = Player.getInstance();
        this.invaders = new ArrayList<>();
        this.setInvaderPointValue(Invader.BASE_POINT_VALUE);
        this.setScore(0);
        this.bgVolume = 0.5;
        this.hud = new HUD();
        this.paused = false;
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

    public int getInvaderPointValue() {
        return invaderPointValue;
    }

    public void setInvaderPointValue(int invaderPointValue) {
        this.invaderPointValue = invaderPointValue;
    }

    public HUD getHud() {
        return hud;
    }

    public void setDifficulty(Difficulty.Level difficultyLevel){
        this.difficulty = new Difficulty(difficultyLevel);
    }

    public String getPlayerShipImage(){
        return this.playerShipImage;
    }

    public boolean isPaused(){
        return this.paused;
    }

    public void pause(boolean pause){
        this.paused = pause;
    }

    public void setBgMusic(AudioClip bgMusic){
        this.bgMusic = bgMusic;
    }

    public AudioClip getBgMusic(){
        return this.bgMusic;
    }

    public double getBgVolume(){
        return this.bgVolume;
    }

    public AudioClip getIntroMusic(){
        return this.introMusic;
    }

    public void setIntroMusic(AudioClip introMusic){
        this.introMusic = introMusic;
    }

    public void setBgVolume(double volume){
        this.bgVolume = volume;
    }

    public static void setPlayerLook(String style, String color){
        playerStyle = style;
        playerColor = color;
        playerShipImage = "assets/spaceshooter/PNG/"+playerStyle+"_"+playerColor+".png";
    }
}
