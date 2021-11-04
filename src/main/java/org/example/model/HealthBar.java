package org.example.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HealthBar extends GameObject{

    private int maxHealth;
    private Rectangle bar;
    private Rectangle maxBar;
    private Rectangle border;
    private double borderSize;

    public HealthBar(){
        this.maxBar = new Rectangle();
        this.bar = new Rectangle();
        this.border = new Rectangle();
        this.maxBar.setFill(Color.RED);
        this.bar.setFill(Color.GREEN);
        this.border.setFill(Color.WHITE);
        this.borderSize = 2;
        this.setImageView(null);
    }

//    public HealthBar(int maxHealth){
//        this.maxHealth = maxHealth;
//        this.maxBar = new Rectangle();
//        this.bar = new Rectangle();
//        this.maxBar.setFill(Color.RED);
//        this.bar.setFill(Color.GREEN);
//        this.border.setFill(Color.WHITE);
//        this.setImageView(null);
//    }

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

    public void setMaxSize(double width, double height){
        this.border.setWidth(width+this.borderSize);
        this.border.setHeight(height+this.borderSize);
        this.maxBar.setWidth(width);
        this.maxBar.setHeight(height);
        this.bar.setWidth(width);
        this.bar.setHeight(height);
    }

    public void setWidth(double width){
        this.bar.setWidth(width);
    }

    public double getWidth(){
        return this.bar.getWidth();
    }

    public void setHeight(double height){
        this.bar.setHeight(height);
    }

    public double getHeight(){
        return this.bar.getHeight();
    }

    public Rectangle getBar(){
        return this.bar;
    }

    public Rectangle getMaxBar(){
        return this.maxBar;
    }

    public Rectangle getBorder(){
        return this.border;
    }

    @Override
    public void setPosX(double posX){
        super.setPosX(posX);
        this.border.setLayoutX(posX-(this.borderSize/2));
        this.maxBar.setLayoutX(posX);
        this.bar.setLayoutX(posX);
    }

    @Override
    public void setPosY(double posY){
        super.setPosY(posY);
        this.border.setLayoutY(posY-(this.borderSize/2));
        this.maxBar.setLayoutY(posY);
        this.bar.setLayoutY(posY);
    }

    /**
     * Amount to decrease current health compared to maxhealthbar size
     * ie. 2 will decrease health by half of the maximum health
     * @param decreaseAmount ratio of maxhealthbar width to decrease current health by
     */
    public void decreaseHealth(double decreaseAmount){
        double maxWidth = this.maxBar.getWidth();
        double currentWidth = this.bar.getWidth();
        this.bar.setWidth(currentWidth-(maxWidth/decreaseAmount));
    }
}
