package com.mauja.maujaadventures.modele;

import javafx.scene.image.Image;

public abstract class Entite {
    private Position position;
    private Image image;

    public Entite(int x, int y,Image image){
        this.position = position;
        this.positionY = y;
        this.image=image;
    }

    public int getPositionX(){
        return positionX;
    }

    public void setPositionX(int positionX){
        this.positionX = positionX;
    }

    public int getPositionY(){
        return positionY;
    }

    public void setPositionY(int y){
        this.positionY = y;
    }

    public Image getImage() { return image; }

    protected void setImage() { this.image = image; }
}
