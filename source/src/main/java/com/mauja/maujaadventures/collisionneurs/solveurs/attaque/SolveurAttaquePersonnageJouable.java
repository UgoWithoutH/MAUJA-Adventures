package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurAttaquePersonnageJouable extends SolveurAttaque{
    /**
     * Constructeur de la classe SolveurCollision
     *
     * @param carte Carte actuelle sur laquelle se déroule le projet
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public SolveurAttaquePersonnageJouable(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif e1, Attaque attaque) {
        PersonnageJouable pj = (PersonnageJouable) e1;
        pj.setPointsDeVie(pj.getPointsDeVie() - pj.getAttaque().getDegats());
        if (pj.getPointsDeVie() <= 0) {
            cartecourante.supprimerElementInteractif(pj);
        }
    }
}
