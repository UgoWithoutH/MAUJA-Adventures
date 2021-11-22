package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import javafx.scene.image.Image;

public class PersonnageJouable extends Personnage {

    private int attaque;

    public PersonnageJouable(int x, int y, int attaque, Image image, Collision collision){
        super(x,y,image,collision);
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
