package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.logique.MementoPosition;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

public class DeplaceurBasique extends Deplaceur {
    private CollisionneurCarte collisionneurCarte;

    public DeplaceurBasique(Carte carte) {
        super(carte);
        collisionneurCarte = new CollisionneurCarte();
    }

    @Override
    public boolean deplace(Entite entite, Direction direction, boolean gestionCollisions) {
        if (entite == null || direction == null) {
            return false;
        }

        Rectangle collisionEntite;
        Position positionEntite = calculPositionFuture(entite, direction,
                entite.getVelocite().getX(), entite.getVelocite().getY());
        if (positionEntite == null)
            return false;

        if (gestionCollisions) {
            collisionEntite = new Rectangle(
                    new Position(entite.getCollision().getPosition().getX() + positionEntite.getX(),
                            entite.getCollision().getPosition().getY() + positionEntite.getY()),
                    entite.getCollision().getDimension());

            if (collisionneurCarte.collisionne(collisionEntite, carteCourante)) {
                return ajustementDeplacement(entite, direction,
                        entite.getVelocite().getX(), entite.getVelocite().getY());
            }
        }
        entite.installerMemento(new MementoPosition(positionEntite));
        entite.setDirection(direction);
        return true;
    }

    private boolean ajustementDeplacement(Entite entite, Direction direction, double incrementX, double incrementY) {
        Rectangle collisionEntite;
        Position positionEntite;

        do {
            incrementX--; incrementY--;
            positionEntite = calculPositionFuture(entite, direction, incrementX, incrementY);
            if (positionEntite == null) {
                return false;
            }
            collisionEntite = new Rectangle(
                    new Position(entite.getCollision().getPosition().getX() + positionEntite.getX(),
                            entite.getCollision().getPosition().getY() + positionEntite.getY()),
                    entite.getCollision().getDimension());
            if (!collisionneurCarte.collisionne(collisionEntite, carteCourante)) {
                entite.installerMemento(new MementoPosition(positionEntite));
                entite.setDirection(direction);
                return true;
            }
        }
        while (incrementX > 0 && incrementY > 0);
        return false;
    }
    
    private Position calculPositionFuture(Entite entite, Direction direction, double incrementX, double incrementY) {
        Position positionEntite;
        switch(direction) {
            case DROITE:
                positionEntite = new Position(entite.getPosition().getX() + incrementX,
                        entite.getPosition().getY());
                break;
            case GAUCHE:
                positionEntite = new Position(entite.getPosition().getX() - incrementY,
                        entite.getPosition().getY());
                break;
            case BAS:
                positionEntite = new Position(entite.getPosition().getX(),
                        entite.getPosition().getY() + incrementY);
                break;
            case HAUT:
                positionEntite = new Position(entite.getPosition().getX(),
                        entite.getPosition().getY() - incrementY);
                break;
            default:
                return null;
        }
        return positionEntite;
    }
}
