package org.example.model;

import javafx.geometry.Point2D;

public abstract class Character extends GameObject{
    private double speed;
    private int maxHealth;
    private int currentHealth;
    private boolean alive;
    protected HitBox hitBox;

    public void updateHitBox(){
        Point2D topLeft = new Point2D(this.getPosX(),this.getPosY());
        Point2D topRight = new Point2D(this.getPosX()+this.getWidth(), this.getPosY());
        Point2D bottomLeft = new Point2D(this.getPosX(), this.getPosY()+this.getHeight());
        Point2D bottomRight = new Point2D(this.getPosX()+this.getWidth(), this.getPosY()+this.getWidth());
        this.hitBox.set(topLeft, topRight, bottomLeft, bottomRight);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
