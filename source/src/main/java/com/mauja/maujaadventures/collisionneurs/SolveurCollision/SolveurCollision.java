package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.interactions.ElementInteractif;

public interface SolveurCollision {
    /**
     * Interface de la résolution de collision
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @return true si il y a collision sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean resoud(ElementInteractif e1, ElementInteractif e2);
}
