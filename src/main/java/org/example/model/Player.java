package org.example.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player extends Character{
    private static Player player = new Player();
    private String name;
    private boolean movingLeft;
    private boolean movingRight;
    private boolean shooting;
    private Laser laser;


    private Player(){
        this.movingLeft = false;
        this.movingRight = false;
        this.shooting = false;
        this.setMoveSpeed(5);
        this.setDirection(0);
        this.laser = null;
        this.hitBox = new HitBox();
        this.updateHitBox();
    };

    public static Player getInstance(){
        return player;
    }

    public void move(){
        this.setPosX(this.getPosX()+(this.getMoveSpeed()*this.getDirection()));
        this.updateHitBox();
    }

    public Laser shootLaser(){
        this.laser = new Laser();
        laser.setImage(new Image("assets/spaceshooter/PNG/Lasers/laserGreen04.png"));
        laser.setImageView(laser.getImage());
        laser.setHeight(20);
        laser.setWidth(5);
        laser.setPosX(this.getPosX()+(this.getWidth()/2)-2);
        laser.setPosY(this.getPosY()-laser.getHeight());
        this.laser.setActive(true);
        this.laser.setDirection(-1);
        return this.laser;
    }

    public void destroyLaser(){
        this.laser = null;
    }

    public Laser getLaser() {
        return this.laser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isShooting() {
        return shooting;
    }

    public void setShooting(boolean shooting) {
        this.shooting = shooting;
    }

    public boolean hasActiveRocket(){
        if(this.laser == null){
            return false;
        }
        return true;
    }
}
