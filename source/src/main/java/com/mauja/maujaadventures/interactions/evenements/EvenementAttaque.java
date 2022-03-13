package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.collisionneurs.solveurs.attaque.SolveurAttaque;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;

public class EvenementAttaque extends Evenement {
    private Carte carte;

    public EvenementAttaque(Carte carteCourante, ElementInteractif elementInteractif)
            throws IllegalArgumentException {
        super(elementInteractif);
        if (carteCourante == null) {
            throw new IllegalArgumentException("La carte passée en paramètre ne peut pas être nulle.");
        }
    }

    @Override
    public void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu) {
        SolveurAttaque solveurAttaque = new SolveurAttaque(tableauDeJeu.getCarteCourante());

        for (ElementInteractif elementInter : tableauDeJeu.getCarteCourante().getLesElementsInteractifs()){
            if(elementInteractif != elementInter) {
                solveurAttaque.resoud(elementInteractif, elementInter, scenarios, tableauDeJeu);
            }
        }
    }
}
