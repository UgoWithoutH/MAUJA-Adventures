package com.mauja.maujaadventures.collisionneurs.SolveurCollision;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.EtatAction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurPersonnageJouableEnnemi extends SolveurCollision{

    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurPersonnageJouableEnnemi(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif e1, ElementInteractif e2, ElementInteractif memento) {
        PersonnageJouable personnageJouable = (PersonnageJouable) e1;
        Ennemi ennemi = (Ennemi) e2;

        personnageJouable.setPosition(memento.getPosition());
        personnageJouable.setPointsDeVie(personnageJouable.getPointsDeVie() - ennemi.getAttaque().getDegats());
    }
}
