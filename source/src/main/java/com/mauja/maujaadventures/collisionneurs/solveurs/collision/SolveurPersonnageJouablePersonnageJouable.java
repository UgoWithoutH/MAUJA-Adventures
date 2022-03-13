package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurPersonnageJouablePersonnageJouable extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurPersonnageJouablePersonnageJouable
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouablePersonnageJouable(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un PersonnageJouable et un PersonnageJouable
     * Si il y a collision entre personnage jouable et personnage jouable l'ennemi ne peut pas se déplacer
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        e1.restorerMemento();
    }
}
