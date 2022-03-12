package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.monde.Carte;

public class DeplaceurDeDestructible extends Deplaceur {
    private Deplaceur deplaceur;

    public DeplaceurDeDestructible(Carte carte, Deplaceur deplaceur) {
        super(carte);
        if (deplaceur == null) {
            throw new IllegalArgumentException("Le déplaceur du destructible ne peut pas être null.");
        }
        this.deplaceur = deplaceur;
    }

    @Override
    public boolean deplace(Entite entite, Direction direction, boolean gestionCollisions) {
        boolean estDeplace = deplaceur.deplace(entite, direction, true);
        if (!estDeplace) {
            carteCourante.supprimerEntite(entite);
            return false;
        }
        return true;
    }
}
