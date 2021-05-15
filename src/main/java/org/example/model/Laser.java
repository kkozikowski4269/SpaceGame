package org.example.model;

public class Laser extends GameObject{
    private boolean active;
    private int direction;
    private double moveSpeed;

    public Laser(){
        this.setDirection(0);
        this.setMoveSpeed(5);
        this.hitBox = new HitBox();
        this.updateHitBox();
    }

    public void move(){
        this.setPosY(this.getPosY()+(this.getMoveSpeed()*this.getDirection()));
        this.updateHitBox();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

}
