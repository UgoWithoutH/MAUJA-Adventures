package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.deplaceurs.DeplaceurEntite;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.PersonnageJouable;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComportementPoursuite implements Comportement{
    private static final List<Direction> DIRECTIONS_POSSIBLES = Arrays.asList(Direction.values());
    private PersonnageJouable joueur;

    private DeplaceurEntite deplaceur;

    public ComportementPoursuite(Carte carte, PersonnageJouable joueur) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du comportement de poursuiveur ne peut pas "
                    + "être nulle.");
        }
        deplaceur = new DeplaceurEntite(carte);
        this.joueur = joueur;

    }

    @Override
    public void agit(Vivant vivant, float temps) {
        if (joueur.getPosition().getX() > vivant.getPosition().getX()){
            deplaceur.deplace(vivant, temps, Direction.DROITE, true);
        }
        if (joueur.getPosition().getX() < vivant.getPosition().getX()){
            deplaceur.deplace(vivant, temps, Direction.GAUCHE, true);
        }
        if (joueur.getPosition().getY() > vivant.getPosition().getY()){
            deplaceur.deplace(vivant, temps, Direction.BAS, true);
        }
        if (joueur.getPosition().getY() < vivant.getPosition().getY()){
            deplaceur.deplace(vivant, temps, Direction.HAUT, true);
        }

    }
}
