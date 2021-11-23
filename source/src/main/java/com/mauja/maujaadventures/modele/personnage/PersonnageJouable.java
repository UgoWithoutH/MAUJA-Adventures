package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Position;

public class PersonnageJouable extends Personnage {

    private int attaque;
    
    public PersonnageJouable(Position position, String image, Collision collision, int attaque){
        super(position, image, collision);
        this.attaque = attaque;
    }

    @Override
    public String toString() {
        return "PersonnageJouable{" +
                " attaque=" + attaque +
                " Position= " + " x : " + getPosition().getPositionX() + " y : " + getPosition().getPositionY() +
                " Collision=" + getCollision().getZoneCollision() +
                '}';
    }
}
