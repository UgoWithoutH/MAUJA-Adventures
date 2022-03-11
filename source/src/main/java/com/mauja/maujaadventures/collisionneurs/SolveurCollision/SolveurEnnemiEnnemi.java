package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurEnnemiEnnemi extends SolveurCollision{
    /**
     * Constructeur de la classe SolveurEnnemiEnnemi
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurEnnemiEnnemi(Carte carte) {
        super(carte);
    }

    /**
     * Resolution de la collision entre deux ennemis
     * Lorsqu'il y a collision entre 2 ennemi, leur position n'est pas changé le déplacement n'a pas pu s'effectuer
     * @param e1 Element interactif du première élément à tester la collision
     * @param e2 Element interactif du second élément à tester
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2, ElementInteractif memento) {
        Ennemi ennemi = (Ennemi) e1;
        Ennemi ennemi2 = (Ennemi) e2;
        ennemi.setPosition(memento.getPosition());
        //ennemi2.setPosition(ennemi2.getPosition());
    }
}
