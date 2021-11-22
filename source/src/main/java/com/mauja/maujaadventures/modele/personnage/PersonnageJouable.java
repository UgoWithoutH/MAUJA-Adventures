package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import javafx.scene.image.Image;

public class PersonnageJouable extends Personnage {

    private int attaque;

<<<<<<< HEAD
    public PersonnageJouable(int x, int y, int attaque, ImageSource image){
        super(x,y,image);
=======
    public PersonnageJouable(int x, int y, int attaque, Image image, Collision collision){
        super(x,y,image,collision);
>>>>>>> 02edb04c2e8cf920a9ed0983ea45a59fc418cfa3
        this.attaque = attaque;
    }

    @Override
    public String toString() {
        return "PersonnageJouable{" +
                " attaque=" + attaque +
                " Position= " + " x : " + getX() + " y : " + getY() +
                " Collision=" + getCollision().getZoneCollision() +
                '}';
    }
}
