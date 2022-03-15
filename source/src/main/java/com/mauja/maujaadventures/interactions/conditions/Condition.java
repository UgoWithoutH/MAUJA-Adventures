package com.mauja.maujaadventures.interactions.conditions;

import com.mauja.maujaadventures.interactions.elements.Balise;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

public abstract class Condition extends Balise {
    public abstract boolean verificationCondition(ElementInteractif elementInteractif, TableauDeJeu tableauDeJeu);

    @Override
    public String toString() {
        return "Condition{}";
    }
}
