package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Entite;
import javafx.scene.image.Image;

public abstract class Personnage extends Entite { //pour éventuellement des PNJ

    public Personnage(int x, int y, Image image, Collision collision){
        super(x,y,image,collision);
    }

}
