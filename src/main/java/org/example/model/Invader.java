package org.example.model;

public class Invader extends Character{

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
}
