package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ImageSource;
import javafx.scene.image.Image;

public abstract class Entite {
    private int x;
    private int y;
<<<<<<< HEAD
    private ImageSource image;

    public Entite(int x, int y, ImageSource image){
=======
    private Image image;
    private Collision collision;

    public Entite(int x, int y,Image image, Collision collision){
>>>>>>> 02edb04c2e8cf920a9ed0983ea45a59fc418cfa3
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

    public ImageSource getImage() { return image; }

<<<<<<< HEAD
    protected void setImage() {
=======
    protected void setImage() { this.image = image; }

    public Collision getCollision() {
        return collision;
    }

    public void setCollision(Collision collision) {
        this.collision.setZoneCollision(collision.getZoneCollision());
>>>>>>> 02edb04c2e8cf920a9ed0983ea45a59fc418cfa3
    }
}
