package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.monde.Carte;

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

        System.out.println("Collisions : " + coinGauche + " droite : " + coinDroite + " haut : " + coinSuperieur + " bas : " + coinInferieur);

        Rectangle collisionTuileRelative, collisionTuileAbsolue;

        if (coinInferieur >= hauteurCarte || coinInferieur < 0
                || coinSuperieur >= hauteurCarte || coinSuperieur < 0
                || coinDroite >= largeurCarte || coinDroite < 0
                || coinGauche >= largeurCarte || coinGauche < 0) {
            return true;
        }

        for (int k = 0; k < carte.getLaCarte().length; k++) {
            for(int y = coinGauche; y <= coinDroite; y++) {
                for (int x = coinSuperieur; x <= coinInferieur; x++) {
                    collisionTuileRelative = carte.getTuile(y, x, k).getCollision();
                    if (collisionTuileRelative != null) {
                        collisionTuileAbsolue = new Rectangle(collisionTuileRelative.getPosition().getX() + y * largeurTuile,
                                collisionTuileRelative.getPosition().getY() + x * hauteurTuile,
                                collisionTuileRelative.getDimension());
                        if (collisionneur.collisionne(collision, collisionTuileAbsolue)) {
                            System.out.println("COLLISION DETECTEE");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
