package org.example.model;

import javafx.geometry.Point2D;

public class HitBox {
    private Point2D topLeft;
    private Point2D topRight;
    private Point2D bottomLeft;
    private Point2D bottomRight;

    public HitBox(){}

    public Point2D getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Point2D topLeft) {
        this.topLeft = topLeft;
    }

    public Point2D getTopRight() {
        return topRight;
    }

    public void setTopRight(Point2D topRight) {
        this.topRight = topRight;
    }

    public Point2D getBottomLeft() {
        return bottomLeft;
    }

    public void setBottomLeft(Point2D bottomLeft) {
        this.bottomLeft = bottomLeft;
    }

    public Point2D getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point2D bottomRight) {
        this.bottomRight = bottomRight;
    }

    public void set(Point2D topLeft, Point2D topRight, Point2D bottomLeft, Point2D bottomRight){
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    public boolean isCollidingWith(HitBox collision){
        if(collision.topLeft.getX() >= this.topLeft.getX() &&
                collision.topLeft.getX() <= this.topRight.getX() &&
                collision.topLeft.getY() >= this.topLeft.getY() &&
                collision.topLeft.getY() <= this.bottomLeft.getY()){
            return true;
        }
        if(collision.topRight.getX() >= this.topLeft.getX() &&
                collision.topRight.getX() <= this.topRight.getX() &&
                collision.topRight.getY() >= this.topLeft.getY() &&
                collision.topRight.getY() <= this.bottomLeft.getY()){
            return true;
        }
        if(collision.bottomLeft.getX() >= this.topLeft.getX() &&
                collision.bottomLeft.getX() <= this.topRight.getX() &&
                collision.bottomLeft.getY() >= this.topLeft.getY() &&
                collision.bottomLeft.getY() <= this.bottomLeft.getY()){
            return true;
        }
        if(collision.bottomRight.getX() >= this.topLeft.getX() &&
                collision.bottomRight.getX() <= this.topRight.getX() &&
                collision.bottomRight.getY() >= this.topLeft.getY() &&
                collision.bottomRight.getY() <= this.bottomLeft.getY()){
            return true;
        }


        return false;
    }
}
