package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Vivant;
import com.mauja.maujaadventures.logique.Rectangle;

public class Attaqueur {

    public Attaqueur() {

    }

    public void attaque(Vivant vivant, Direction direction) {
        Rectangle collisionAttaque;

        if (vivant == null) {
            return;
        }new Rectangle(tableauDeJeu.getJoueur() + tableauDeJeu.getJoueur().getCollision().getPosition().getX()
                + tableauDeJeu.getJoueur().getCollision().getDimension().getLargeur(),
                tableauDeJeu.getJoueur().getPosition().getY() +
                        (tableauDeJeu.getJoueur().getDimension().getHauteur() - tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getHauteur()) / 2,
                tableauDeJeu.getJoueur().getAttaque().getCollision().getDimension().getLargeur(),
                tableauDeJeu.getJoueur().getCollision().getDimension().getHauteur());

        switch (direction) {
            case DROITE -> collisionAttaque = new Rectangle(vivant.getPosition().getX()
                    + vivant.getCollision().getPosition().getX()
                    + vivant.getCollision().getDimension().getLargeur(),
                    vivant.getPosition().getY()
                    + (vivant.getDimension().getHauteur() - vivant.getAttaque().getCollision().getDimension().getHauteur()) / 2,
                    vivant.getAttaque().getCollision().getDimension().getLargeur(),
                    vivant.getCollision().getDimension().getHauteur());
        }
    }
}
