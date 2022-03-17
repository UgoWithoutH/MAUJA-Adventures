package com.mauja.maujaadventures.collisionneurs.solveurs.attaque;

import com.mauja.maujaadventures.entites.Ennemi;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Solveur d'attaque avec un Ennemi.
 * L'ennemi perd le nombre de point de vie que l'attaque fait des dégâts
 * Si l'ennemi a 0 point de vie supprime l'ennemi
 */
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
