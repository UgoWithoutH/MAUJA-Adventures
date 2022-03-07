package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
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
        PersonnageJouable pj1 = (PersonnageJouable) e1;
        PersonnageJouable pj2 = (PersonnageJouable) e2;
        pj1.setPosition(pj1.getPosition());
        pj1.setDirection(pj1.getDirection());
        pj2.setPosition(pj2.getPosition());
        pj2.setDirection(pj2.getDirection());
    }
}
