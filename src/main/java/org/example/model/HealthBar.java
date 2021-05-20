package org.example.model;

public class HealthBar extends GameObject{

    private int maxHealth;

    public HealthBar(){}

    public HealthBar(int maxHealth){
        this.setMaxHealth(maxHealth);
    }

    @Override
    public HitBox getHitBox() {
        return null;
    }

    @Override
    public void updateHitBox() {
        return;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }
}
