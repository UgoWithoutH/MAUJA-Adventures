package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.Action;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public abstract class Evenement {
    protected ElementInteractif elementInteractif;
    protected TableauDeJeu tableauDeJeu;

    public Evenement(TableauDeJeu tableauDeJeu) {
        this.elementInteractif = tableauDeJeu.getJoueur();
        this.tableauDeJeu = tableauDeJeu;
    }

    public abstract void traitement(List<Scenario> scenarios);

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }
}
