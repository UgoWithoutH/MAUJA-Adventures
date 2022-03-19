package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.interactions.Scenario;
import com.mauja.maujaadventures.interactions.elements.ElementInteractif;
import com.mauja.maujaadventures.jeu.TableauDeJeu;

import java.util.List;

public class EvenementNull extends Evenement {
    public EvenementNull(ElementInteractif elementInteractif) throws IllegalArgumentException {
        super(elementInteractif);
    }

    @Override
    public void traitement(TableauDeJeu tableauDeJeu) {
        // Ne fait rien.
    }
}
