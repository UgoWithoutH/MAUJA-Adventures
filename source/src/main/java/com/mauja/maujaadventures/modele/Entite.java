package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.image.Image;

public abstract class Entite {
    private int x;
    private int y;
    private ImageSource image;

    public Entite(int x, int y, ImageSource image){
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

    public ImageSource getImage() { return image; }

    protected void setImage() {
    }
}
