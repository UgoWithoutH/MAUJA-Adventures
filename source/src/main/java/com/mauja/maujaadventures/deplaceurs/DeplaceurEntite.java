package com.mauja.maujaadventures.deplaceurs;

import com.mauja.maujaadventures.collisionneurs.CollisionneurCarte;
import com.mauja.maujaadventures.entites.Direction;
import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.entites.Projectile;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

public class DeplaceurEntite {
    private Carte carteCourante;
    private CollisionneurCarte collisionneurCarte;

    public DeplaceurEntite(Carte carte) {
        if (carte == null) {
            throw new IllegalArgumentException("La carte passée en paramètre du déplaceur ne peut pas être nulle.");
        }
        carteCourante = carte;
        collisionneurCarte = new CollisionneurCarte();
    }

    /**
     * Méthode permettant le déplacement de l'entite en la modifiant avec son setter
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean deplace(Entite entite, float temps, Direction direction, boolean gestionCollisions) {
        Position positionEntite;
        Rectangle collisionEntite;

        if (direction == Direction.DROITE) {
            positionEntite = new Position(entite.getPosition().getX() + entite.getVelocite().getX(),
                    entite.getPosition().getY());
        }
        else if (direction == Direction.GAUCHE) {
            positionEntite = new Position(entite.getPosition().getX() - entite.getVelocite().getX(),
                    entite.getPosition().getY());
        }
        else if (direction == Direction.BAS) {
            positionEntite = new Position(entite.getPosition().getX(),
                    entite.getPosition().getY() + entite.getVelocite().getY());
        }
        else if (direction == Direction.HAUT) {
            positionEntite = new Position(entite.getPosition().getX(),
                    entite.getPosition().getY() - entite.getVelocite().getY());
        }
        else {
            return false;
        }

        if (gestionCollisions) {
            collisionEntite = new Rectangle(
                    new Position(entite.getCollision().getPosition().getX() + positionEntite.getX(),
                            positionEntite.getY() + entite.getCollision().getPosition().getY()),
                    entite.getCollision().getDimension());

            if (!collisionneurCarte.collisionne(collisionEntite, carteCourante)) {
                entite.setPosition(positionEntite);
                entite.setDirection(direction);
                return true;
            }
            else {
                if (entite instanceof Projectile projectile) {
                    carteCourante.supprimerEntite(projectile);
                }
            }
        }
        else {
            entite.setPosition(positionEntite);
            entite.setDirection(direction);
            return true;
        }

        return false;
    }
}
