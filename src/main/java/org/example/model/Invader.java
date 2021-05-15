package org.example.model;

public class Invader extends Character{

    public Invader(){
        this.hitBox = new HitBox();
        this.updateHitBox();
    }
}
