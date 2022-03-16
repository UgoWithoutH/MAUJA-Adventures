package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

public class SolveurPersonnageJouableEnnemi extends SolveurAttaque{
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
    public void resoud(ElementInteractif e1, ElementInteractif e2) {
        PersonnageJouable pj = (PersonnageJouable) e1;
        Ennemi ennemi = (Ennemi) e2;
        ennemi.setPointsDeVie(ennemi.getPointsDeVie() - pj.getAttaque().getDegats());
        if (ennemi.getPointsDeVie() <= 0) {
            cartecourante.supprimerElementInteractif(ennemi);
        }
    }
}
