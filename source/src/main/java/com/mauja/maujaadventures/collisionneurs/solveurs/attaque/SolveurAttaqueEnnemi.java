package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.monde.Carte;

public class SolveurAttaqueEnnemi extends SolveurAttaque {
    public SolveurAttaqueEnnemi(Carte carte) {
        super(carte);
    }

    @Override
    public void resoud(ElementInteractif elementInteractif, Attaque attaque) {
        Ennemi ennemi = (Ennemi) elementInteractif;
        ennemi.setPointsDeVie(ennemi.getPointsDeVie() - ennemi.getAttaque().getDegats());
        if (ennemi.getPointsDeVie() <= 0) {
            cartecourante.supprimerElementInteractif(ennemi);
        }
    }
}
