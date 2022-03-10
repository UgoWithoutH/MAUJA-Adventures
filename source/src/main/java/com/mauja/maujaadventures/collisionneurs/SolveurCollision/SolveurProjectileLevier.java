package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurProjectileLevier extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurLevierProjectile
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurProjectileLevier(Carte carte) {
        super(carte);
    }

    /**
     * Résolution de la collision entre un Levier et un projectile
     * Si il y a collision et que le projectile puissent activer le levier grâce à une condition alors on active le levier
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2, ElementInteractif memento) {
        cartecourante.supprimerEntite(e1);
        /*Levier levier = (Levier) e1;
        levier.setActive(true);*/
    }
}
