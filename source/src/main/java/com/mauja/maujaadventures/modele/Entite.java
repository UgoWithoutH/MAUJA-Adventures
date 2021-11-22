package com.mauja.maujaadventures.modele;

import javafx.scene.image.Image;

public abstract class Entite {
    private int x;
    private int y;
    private Image image;
    private Collision collision;

    public Entite(int x, int y,Image image, Collision collision){
        this.x = x;
        this.y = y;
        this.image=image;
        this.collision = collision;
    }

    public int getX(){
        return x;
    }

    public void setX(int x){
        this.x = x;
    }

    public int getY(){
        return y;
    }

    public void setY(int y){
        this.y = y;
    }

    public Image getImage() { return image; }

    protected void setImage() { this.image = image; }

    public Collision getCollision() {
        return collision;
    }

    public void setCollision(Collision collision) {
        this.collision.setZoneCollision(collision.getZoneCollision());
    }
}
