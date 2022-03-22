package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.monde.Carte;

public abstract class Deplaceur {
    protected Carte carteCourante;

    public Deplaceur(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du déplaceur ne peut pas être nulle.");
        }
        carteCourante = carte;
    }

    public abstract boolean deplace(Entite entite, Direction direction, boolean gestionCollisions);
}
