package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.jeu.TableauDeJeu;

public abstract class Condition extends Balise {
    public abstract boolean verificationCondition(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu);


    @Override
    public String toString() {
        return "Condition{}";
    }
}
