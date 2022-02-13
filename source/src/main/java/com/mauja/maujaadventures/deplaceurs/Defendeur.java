package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.PersonnageJouable;

public class Defendeur {
    public void defendre(PersonnageJouable joueur, float temps) {
        joueur.setEtatAction(EtatAction.SE_PROTEGE);
    }
}
