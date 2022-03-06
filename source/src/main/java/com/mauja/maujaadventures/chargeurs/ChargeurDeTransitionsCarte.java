package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.monde.Carte;

import java.util.List;
import java.util.Map;

public interface ChargeurDeTransitionsCarte {
    Map<TransitionCarte, TransitionCarte> charge(String chemin, List<Carte> lesCartes);
}
