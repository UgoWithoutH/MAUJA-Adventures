package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur associés à une collision entre 2 destructible, lors de la collision les 2 sont détruits
 */
public class SolveurDestructibleDestructible extends SolveurCollision {

    /**
     * Constructeur de la classe SolveurProjectileProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurDestructibleDestructible(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un Destructible et un destructible
     * Les 2 projectiles sont détruit lorsqu'il rentre en collision
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        carteCourante.supprimerElementInteractif(e1);
        carteCourante.supprimerElementInteractif(e2);
    }
}
