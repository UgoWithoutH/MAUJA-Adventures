package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Levier;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurPersonnageJouableLevier extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurLevierPersonnageJouable
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableLevier(Carte carte) {
        super(carte);
    }

    /**
     * Resolution de la collision d'un type Levier et PersonnageJouable
     * Si il y a collision et que le personnage jouable et décide d'activer le levier alors on active le levier
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2, ElementInteractif memento) {
        e1.setPosition(memento.getPosition());
        Levier levier = (Levier) e1;
        levier.setActive(true);
    }
}
