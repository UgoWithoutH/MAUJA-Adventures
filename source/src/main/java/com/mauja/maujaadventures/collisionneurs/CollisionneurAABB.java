package com.mauja.maujaadventures.collisionneurs;

import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

/**
 * Collisionneur entre 2 rectangle. renvoie true si collision
 */
public class CollisionneurAABB {
    /**
     * Méthode colission permet de voir si une collision est visible
     * @param rectangle1 Rectangle de la première collision
     * @param rectangle2 Rectangle de la seconde collision
     * @return true si il y a une collision sinon false
     */
    public boolean collisionne(Rectangle rectangle1, Rectangle rectangle2) {
        double gauche, droite, haut, bas;
        if (rectangle1 == null || rectangle2 == null) {
            return false;
        }
        Position position1 = rectangle1.getPosition();
        Position position2 = rectangle2.getPosition();

        gauche = Math.max(position1.getX(), position2.getX());
        droite = Math.min(position1.getX() + rectangle1.getDimension().getLargeur(),
                position2.getX() + rectangle2.getDimension().getLargeur());
        haut = Math.max(position1.getY(), position2.getY());
        bas = Math.min(position1.getY() + rectangle1.getDimension().getHauteur(),
                position2.getY() + rectangle2.getDimension().getHauteur());

        return (gauche < droite) && (haut < bas);
    }
}
