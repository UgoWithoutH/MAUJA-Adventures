package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurAttPersonnageJouableProjectile extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurAttPersonnageJouableProjectile(Carte carte) {
        super(carte);
    }

    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        //Ne fait rien
    }
}
