package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.Attaque;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Rectangle;

public class AttaqueurAbsolu implements Attaqueur {

    @Override
    public Attaque genererAttaque(Entite entite, Direction direction, Dimension dimension, int degats, float duree) {
        if (entite == null || direction == null || dimension == null) {
            return null;
        }

        Rectangle collisionAttaque = null;

        if (direction == Direction.DROITE) {
            collisionAttaque = new Rectangle(entite.getPosition().getX() + entite.getCollision().getPosition().getX()
                    + entite.getCollision().getDimension().getLargeur(),
                    entite.getPosition().getY() +
                            (entite.getDimension().getHauteur() - dimension.getHauteur()) / 2,
                    dimension.getLargeur(),
                    dimension.getHauteur());
        }
        if (direction == Direction.GAUCHE) {
            collisionAttaque = new Rectangle(entite.getPosition().getX()
                    - entite.getCollision().getDimension().getLargeur(),
                    entite.getPosition().getY() +
                            (entite.getDimension().getHauteur() - dimension.getHauteur()) / 2,
                    dimension.getLargeur(),
                    dimension.getHauteur());
        }

        if (direction == Direction.HAUT) {
            collisionAttaque = new Rectangle(entite.getPosition().getX() +
                    (entite.getDimension().getLargeur() - dimension.getLargeur()) / 2,
                    entite.getPosition().getY() - dimension.getHauteur()
                            + entite.getDimension().getHauteur() - entite.getCollision().getPosition().getY(),
                    dimension.getLargeur(),
                    dimension.getHauteur());
        }

        if (direction == Direction.BAS) {
            collisionAttaque = new Rectangle(entite.getPosition().getX() +
                    (entite.getDimension().getLargeur() - dimension.getLargeur()) / 2,
                    entite.getPosition().getY() + entite.getDimension().getHauteur(),
                    dimension.getLargeur(),
                    dimension.getHauteur());
        }

        return new Attaque(collisionAttaque, duree, degats);
    }
}
