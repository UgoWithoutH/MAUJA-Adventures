package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.Action;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public abstract class Evenement {
    protected ElementInteractif elementInteractif;
    protected ElementInteractif sauvegardeElementInteractif;
    protected TableauDeJeu tableauDeJeu;

    public Evenement(TableauDeJeu tableauDeJeu , ElementInteractif elementInteractif) {
        this.elementInteractif = elementInteractif;
        this.sauvegardeElementInteractif = elementInteractif.clone();
        this.tableauDeJeu = tableauDeJeu;
    }

    public abstract void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu);

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }
}
