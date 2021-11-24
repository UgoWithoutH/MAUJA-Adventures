package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.Entite;
import com.mauja.maujaadventures.modele.Position;

public abstract class Personnage extends Entite { //pour éventuellement des PNJ
    /**
     * Constructeur de la classe Abstract Personnage qui appelle sa classe mère Entite.
     *
     * @param position Correspond à la position du personnage
     * @param image Image du personnage
     * @param collision Elément permettant de distinguer la collision du personnage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Personnage(Position position, String image, Collision collision){
        super(position, image, collision);
    }

}
