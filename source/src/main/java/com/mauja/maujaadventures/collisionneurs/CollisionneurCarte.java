package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

/**
 * Collision entre un élément de la carte et une collision
 */
public class CollisionneurCarte {
    private CollisionneurAABB collisionneur = new CollisionneurAABB();

    public boolean collisionne(Rectangle collision, Carte carte) {
        if (collision == null || carte == null) {
            return false;
        }

        double largeurTuile = carte.getDimensionTuiles().getLargeur();
        double hauteurTuile = carte.getDimensionTuiles().getHauteur();
        double largeurCarte = carte.getDimensionCarte().getLargeur();
        double hauteurCarte = carte.getDimensionCarte().getHauteur();

        int coinGauche = (int) (collision.getPosition().getX() / largeurTuile);;
        int coinSuperieur = (int) (collision.getPosition().getY() / hauteurTuile);
        int coinDroite = (int) ((collision.getPosition().getX() + collision.getDimension().getLargeur())
                / largeurTuile);
        int coinInferieur = (int) ((collision.getPosition().getY() + collision.getDimension().getLargeur())
                / hauteurTuile);

        Rectangle collisionTuileRelative, collisionTuileAbsolue;

        if (coinInferieur >= hauteurCarte || coinInferieur < 0
                || coinSuperieur >= hauteurCarte || collision.getPosition().getX() <= 0
                || coinDroite >= largeurCarte || coinDroite < 0
                || coinGauche >= largeurCarte || collision.getPosition().getY() <= 0) {
            return true;
        }

        for (int k = 0; k < carte.getLaCarte().length; k++) {
            for(int x = coinGauche; x <= coinDroite; x++) {
                for (int y = coinSuperieur; y <= coinInferieur; y++) {
                    collisionTuileRelative = carte.getTuile(x, y, k).getCollision();
                    if (collisionTuileRelative != null) {
                        collisionTuileAbsolue = new Rectangle(collisionTuileRelative.getPosition().getX() + x * largeurTuile,
                                collisionTuileRelative.getPosition().getY() + y * hauteurTuile,
                                collisionTuileRelative.getDimension());
                        if (collisionneur.collisionne(collision, collisionTuileAbsolue)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
