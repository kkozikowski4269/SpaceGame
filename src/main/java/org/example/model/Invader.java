package org.example.model;

public class Invader extends Character{
    public static final int BASE_POINT_VALUE = 100;

    public Invader(){
        this.hitBox = new HitBox();
        this.updateHitBox();
    }

    public boolean isHitBy(Laser laser){
        if(this.hitBox.isCollidingWith(laser.getHitBox())){
            return true;
        }
        return false;
    }

    public void descend(){
        this.setPosY(this.getPosY()+this.getHeight()+10);
    }

}
