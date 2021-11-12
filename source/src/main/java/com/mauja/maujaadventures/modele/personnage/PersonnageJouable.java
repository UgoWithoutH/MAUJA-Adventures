package com.mauja.maujaadventures.modele.personnage;

public class PersonnageJouable extends Personnage {

    private int attaque;

    public PersonnageJouable(int x, int y, int attaque){
        super(x,y);
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
