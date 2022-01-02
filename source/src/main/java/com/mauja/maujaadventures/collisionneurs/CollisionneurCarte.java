package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

public class CollisionneurCarte {
    public boolean collisionne(Rectangle collision, Carte carte) {
        if (collision == null || carte == null || carte.getListeDeCalques().get(1).getListeDeTuiles() == null) {
            return false;
        }

        int coinGauche = (int) (collision.getPosition().getX() / 32);;
        int coinSuperieur = (int) (collision.getPosition().getY() / 32);
        int coinDroite = (int) ((collision.getPosition().getX() + collision.getDimension().getLargeur())
                / 32);
        int coinInferieur = (int) ((collision.getPosition().getY() + collision.getDimension().getLargeur())
                / 32);

        Rectangle collisionTuileRelative, collisionTuileAbsolue;

        for(int x = coinGauche; x <= coinDroite; x++) {
            for(int y = coinSuperieur; y <= coinInferieur; y++) {
                collisionTuileRelative = carte.getListeDeCalques().get(1).getTuile(y, x).getCollision();
                if (collisionTuileRelative != null) {
                    collisionTuileAbsolue = new Rectangle(collisionTuileRelative.getPosition().getX() + x * 32,
                            collisionTuileRelative.getPosition().getY() + y * 32,
                            collisionTuileRelative.getDimension().getLargeur(),
                            collisionTuileRelative.getDimension().getHauteur());
                    if (CollisionneurAABB.collision(collision, collisionTuileAbsolue))
                        return true;
                }
            }
        }
        return false;
    }
}
