package com.mauja.maujaadventures.modele.personnage;

import javafx.scene.image.Image;

public class PersonnageJouable extends Personnage {

    private int attaque;

    public PersonnageJouable(int x, int y, int attaque, Image image){
        super(x,y,image);
        this.attaque = attaque;
    }

    @Override
    public String toString() {
        return "PersonnageJouable{" +
                "attaque=" + attaque +
                "Position= " + " x : " + getX() + " y : " + getY() +
                '}';
    }
}
