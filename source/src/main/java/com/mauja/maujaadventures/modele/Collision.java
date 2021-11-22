package com.mauja.maujaadventures.modele;

import javafx.geometry.Rectangle2D;

public class Collision {
    private Rectangle2D zoneCollision;

    public Collision(Rectangle2D r){
        zoneCollision = r;
    }

    public Rectangle2D getZoneCollision() {
        return zoneCollision;
    }

    public void setZoneCollision(Rectangle2D zoneCollision) {
        this.zoneCollision = zoneCollision;
    }
}
