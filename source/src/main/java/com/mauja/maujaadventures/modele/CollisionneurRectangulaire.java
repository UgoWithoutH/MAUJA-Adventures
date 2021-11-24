package com.mauja.maujaadventures.modele;

import javafx.geometry.Rectangle2D;

public class CollisionneurRectangulaire implements Collisionneur {

    @Override
    public boolean collisionne(Collision collision1, Collision collision2) throws IllegalArgumentException {
        verificationCollision(collision1);
        verificationCollision(collision2);
        Rectangle2D rectangle1 = (Rectangle2D) collision1.getZoneCollision();
        Rectangle2D rectangle2 = (Rectangle2D) collision2.getZoneCollision();
        return rectangle1.intersects(rectangle2);
    }

    private void verificationCollision(Collision collision) throws IllegalArgumentException {
        if (collision == null) {
            throw new NullPointerException("La Collision donnée en paramètre est nulle.");
        }
        if (!(collision instanceof CollisionRectangulaire)) {
            throw new IllegalArgumentException("La Collision donnée en paramètre n'est pas une collision "
                    + "rectangulaire : " + collision.toString());
        }
    }
}
