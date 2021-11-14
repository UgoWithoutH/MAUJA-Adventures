package com.mauja.maujaadventures.modele;

import javafx.scene.image.Image;

public abstract class Entite {
    private int x;
    private int y;
    private Image image;

    public Entite(int x, int y,Image image){
        this.x = x;
        this.y = y;
        this.image=image;
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
}
