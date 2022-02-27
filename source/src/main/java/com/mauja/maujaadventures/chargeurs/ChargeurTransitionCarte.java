package com.mauja.maujaadventures.chargeurs;

import com.mauja.maujaadventures.logique.TransitionCarte;
import com.mauja.maujaadventures.utilitaires.Graphe;

public interface ChargeurTransitionCarte {
    Graphe<TransitionCarte> charge(String chemin);
}
