package org.example.model;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private Image image;
    private ImageView imageView;
    protected HitBox hitBox;

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
        this.imageView.setLayoutX(posX);
    }

    public double getPosY() { return posY; }

    public void setPosY(double posY) {
        this.posY = posY;
        this.imageView.setLayoutY(posY);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        this.imageView.setFitWidth(width);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        this.imageView.setFitHeight(height);
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(Image image) {
        this.imageView = new ImageView(image);
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void updateHitBox(){
        Point2D topLeft = new Point2D(this.getPosX(),this.getPosY());
        Point2D topRight = new Point2D(this.getPosX()+this.getWidth(), this.getPosY());
        Point2D bottomLeft = new Point2D(this.getPosX(), this.getPosY()+this.getHeight());
        Point2D bottomRight = new Point2D(this.getPosX()+this.getWidth(), this.getPosY()+this.getWidth());
        this.hitBox.set(topLeft, topRight, bottomLeft, bottomRight);
    }
}
