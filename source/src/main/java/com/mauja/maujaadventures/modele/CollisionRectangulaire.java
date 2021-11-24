package com.mauja.maujaadventures.modele;

import javafx.geometry.Rectangle2D;

public class CollisionRectangulaire extends Collision {

    private Rectangle2D zoneCollision;

    public CollisionRectangulaire(Position position, double largeur, double hauteur) {
        super(position, largeur, hauteur);
        zoneCollision = new Rectangle2D(position.getPositionX(), position.getPositionY(), largeur, hauteur);
    }

    @Override
    public Rectangle2D getZoneCollision() { return zoneCollision; }

    private void setZoneCollision(Rectangle2D zoneCollision) { this.zoneCollision = zoneCollision; }
}
