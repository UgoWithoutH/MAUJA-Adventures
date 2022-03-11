package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.entites.Vivant;

public class ComportementNull implements Comportement {
    @Override
    public void agit(Vivant vivant, float temps) {
        //Ne fait rien
    }

    @Override
    public void miseAJour(boolean resultatDeplacement) {
        //Ne fait rien
    }
}
