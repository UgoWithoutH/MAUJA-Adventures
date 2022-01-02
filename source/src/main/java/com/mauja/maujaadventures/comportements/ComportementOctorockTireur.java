package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.monde.Carte;

public class ComportementOctorockTireur implements Comportement {
    private Carte carteCourante;

    public ComportementOctorockTireur(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du comportement de tireur ne peut pas "
                    + "être nulle.");
        }
        carteCourante = carte;
    }

    @Override
    public void agit(Vivant vivant, float temps) {

    }
}
