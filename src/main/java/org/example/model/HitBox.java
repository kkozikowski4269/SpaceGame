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
}
