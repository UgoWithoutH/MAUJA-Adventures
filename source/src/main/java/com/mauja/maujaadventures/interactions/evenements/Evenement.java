package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.observateurs.ObservableEvenementiel;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public abstract class Evenement extends ObservableEvenementiel {
    protected ElementInteractif elementInteractif;

    public Evenement(ElementInteractif elementInteractif) throws IllegalArgumentException {
        if (elementInteractif == null) {
            throw new IllegalArgumentException("L'élément interactif passé en paramètre ne peut pas être null.");
        }
        this.elementInteractif = elementInteractif;
    }

    public abstract void traitement(TableauDeJeu tableauDeJeu);

    public ElementInteractif getElementInteractif() {
        return elementInteractif;
    }
}
