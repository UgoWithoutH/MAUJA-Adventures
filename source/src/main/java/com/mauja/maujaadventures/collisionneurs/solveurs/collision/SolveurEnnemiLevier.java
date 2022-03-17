package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur entre un ennemi et un levier restore la position de l'ennemi à sa dernière position avant collision
 */
public class SolveurEnnemiLevier extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurEnnemiLevier
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiLevier(Carte carte) {
        super(carte);
    }

    /**
     * Resolution de la collision entre un levier et un ennemi
     * Lorsqu'il y a un ennemi qui peut activer le levier et que la condition est respecté alors le levier s'active
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        e1.restorerMemento();
    }
}
