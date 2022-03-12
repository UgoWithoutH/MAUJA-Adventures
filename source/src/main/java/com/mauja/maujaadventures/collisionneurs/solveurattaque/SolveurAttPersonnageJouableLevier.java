package com.mauja.maujaadventures.collisionneurs.solveurattaque;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurAttPersonnageJouableLevier extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurAttPersonnageJouableLevier(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        PersonnageJouable pj = (PersonnageJouable) e1;
        Levier levier = (Levier) e2;
        if (collisionneur.collisionne(pj.getAttaque().getCollision(), levier.getCollision())) {
            levier.setActive(true);
        }
    }
}
