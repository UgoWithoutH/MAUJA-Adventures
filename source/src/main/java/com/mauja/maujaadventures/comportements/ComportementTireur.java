package com.mauja.maujaadventures.comportements;

import com.mauja.maujaadventures.deplaceurs.Deplaceur;
import com.mauja.maujaadventures.deplaceurs.DeplaceurBasique;
import com.mauja.maujaadventures.deplaceurs.DeplaceurDeDestructible;
import com.mauja.maujaadventures.entites.Destructible;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ComportementTireur implements Comportement {
    private static final Random ALEATOIRE = new Random();
    private static final List<Direction> DIRECTIONS_POSSIBLES = Arrays.asList(Direction.values());
    private static final int NOMBRE_DIRECTIONS = DIRECTIONS_POSSIBLES.size();
    private static final float INTERVALLE_DEPLACEMENT = 20;
    private static final float INTERVALLE_TIR = 600;
    private static final int NOMBRE_MAXIMUM_TENTATIVES_DEPLACEMENT = 6;

    private Deplaceur deplaceur;
    private Carte carteCourante;
    private int iterations = 0;
    private Direction derniereDirection;
    private int temps;

    public ComportementTireur(Carte carte) throws IllegalArgumentException {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du comportement de tireur ne peut pas "
                    + "être nulle.");
        }
        carteCourante = carte;
        deplaceur = new DeplaceurBasique(carte);
    }

    @Override
    public void agit(Vivant vivant, float temps) {
        this.temps++;
        Direction direction;
        boolean resultatDeplacement;
        int nombreTentatives = 0;

        if (this.temps > INTERVALLE_DEPLACEMENT || iterations != 0) {
            this.temps = 0;
            do {
                if (iterations == 0) {
                    derniereDirection = DIRECTIONS_POSSIBLES.get(ALEATOIRE.nextInt(NOMBRE_DIRECTIONS));
                }
                resultatDeplacement = deplaceur.deplace(vivant, derniereDirection, true);
                nombreTentatives++;
            }
            while (!resultatDeplacement && nombreTentatives < NOMBRE_MAXIMUM_TENTATIVES_DEPLACEMENT);
            iterations++;
            if (iterations == 10) {
                Position positionDestructible = new Position(
                        vivant.getPosition().getX() + vivant.getCollision().getPosition().getX() / 2,
                        vivant.getPosition().getY() + vivant.getCollision().getPosition().getY() / 2);
                Destructible destructible = new Destructible(positionDestructible, new Dimension(20, 20),
                        new Rectangle(0, 0, 20, 20), null, 3,
                        new DeplaceurDeDestructible(carteCourante, deplaceur));
                destructible.setDirection(vivant.getDirection());
                decalageProjectile(destructible, vivant);
                carteCourante.ajouterElementInteractif(destructible);
                iterations = 0;
            }
        }
    }

    private void decalageProjectile(Destructible destructible, Vivant vivant) {
        Position positionProjectile;
        switch (destructible.getDirection()) {
            case DROITE -> positionProjectile = new Position(
                    destructible.getPosition().getX() + vivant.getCollision().getDimension().getLargeur(),
                    destructible.getPosition().getY());
            case GAUCHE -> positionProjectile = new Position(
                    destructible.getPosition().getX() - vivant.getCollision().getDimension().getLargeur(),
                    destructible.getPosition().getY());
            case HAUT -> positionProjectile = new Position(destructible.getPosition().getX(),
                    destructible.getPosition().getY() - vivant.getCollision().getDimension().getHauteur());
            case BAS -> positionProjectile = new Position(destructible.getPosition().getX(),
                    destructible.getPosition().getY() + vivant.getCollision().getDimension().getHauteur());
            default -> {
                return;
            }
        }
        destructible.setPosition(positionProjectile);
    }
}
