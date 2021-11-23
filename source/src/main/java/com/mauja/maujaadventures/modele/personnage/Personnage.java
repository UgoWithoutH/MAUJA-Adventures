package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Entite;
import com.mauja.maujaadventures.modele.Position;

public abstract class Personnage extends Entite { //pour éventuellement des PNJ

    public Personnage(Position position, String image, Collision collision){
        super(position, image, collision);
    }

}
