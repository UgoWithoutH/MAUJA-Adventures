package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur entre un Ennemi et un personnageJouable.
 * Le personnageJouable prend des dégâts en fonction du nombre de point de vie de l'ennemi
 */
public class SolveurEnnemiPersonnageJouable extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurEnnemiPersonnageJouable
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiPersonnageJouable(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un PersonnageJouable et un Ennemi
     * Lorsqu'il y a la collision entre 1 personnage jouable et un ennemi, le personnage jouable subit les dégâts de l'ennemi
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        Ennemi ennemi = (Ennemi) e1;
        PersonnageJouable pj = (PersonnageJouable) e2;
        pj.setPointsDeVie(pj.getPointsDeVie() - ennemi.getAttaque().getDegats());
    }
}
