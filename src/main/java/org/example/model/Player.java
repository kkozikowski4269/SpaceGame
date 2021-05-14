package org.example.model;

public class Player extends Character{
    private static Player player = new Player();
    private String name;

    private Player(){};

    public static Player getInstance(){
        return player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
