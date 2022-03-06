package com.mauja.maujaadventures.interactions.evenements;

import com.mauja.maujaadventures.entites.EtatAction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.interactions.*;
import com.mauja.maujaadventures.jeu.TableauDeJeu;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EvenementDeplacement extends Evenement {

    public EvenementDeplacement(TableauDeJeu tableauDeJeu, ElementInteractif elementInteractif) {
        super(tableauDeJeu, elementInteractif);
    }

    @Override
    public void traitement(List<Scenario> scenarios) {

    }
}
