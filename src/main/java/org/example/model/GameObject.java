package org.example.model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class GameObject {
    private double posX;
    private double posY;
    private double width;
    private double height;
    private Image image;
    private ImageView imageView;

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
}
