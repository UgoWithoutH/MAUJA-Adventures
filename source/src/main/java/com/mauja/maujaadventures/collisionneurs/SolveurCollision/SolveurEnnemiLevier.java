package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.monde.Carte;

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
