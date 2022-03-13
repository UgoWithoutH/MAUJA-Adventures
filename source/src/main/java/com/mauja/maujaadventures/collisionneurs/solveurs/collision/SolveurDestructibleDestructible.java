package com.mauja.maujaadventures.collisionneurs.solveurs.collision;

import com.mauja.maujaadventures.entites.Destructible;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

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
        System.out.println("je supprime proj - proj");
        carteCourante.supprimerEntite(e1);
        carteCourante.supprimerEntite(e2);
    }
}
