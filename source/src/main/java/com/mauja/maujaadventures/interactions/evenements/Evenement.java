package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.Action;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public abstract class Evenement {
    protected ElementInteractif elementInteractif;
    protected ElementInteractif sauvegardeElementInteractif;

    public Evenement(ElementInteractif elementInteractif) throws IllegalArgumentException {
        if (elementInteractif == null) {
            throw new IllegalArgumentException("L'élément interactif passé en paramètre ne peut pas être null.");
        }
        this.elementInteractif = elementInteractif;
        this.sauvegardeElementInteractif = elementInteractif.clone();
    }

    public abstract void traitement(List<Scenario> scenarios, TableauDeJeu tableauDeJeu);

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }
}
