package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Rectangle;
import com.mauja.maujaadventures.modele.Position;

public abstract class Personnage extends Entite { //pour éventuellement des PNJ
    /**
     * Constructeur de la classe Abstract Personnage qui appelle sa classe mère Entite.
     *
     * @param position Correspond à la position du personnage
     * @param rectangle Elément permettant de distinguer la collision du personnage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Personnage(Position position, Rectangle rectangle){
        super(position, rectangle);
    }

}
