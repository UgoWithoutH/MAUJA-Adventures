package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Collision {
    private Rectangle2D zoneCollision;

    public Collision(ProprietesImage i){
        double largeur = i.getImage().getWidth();
        double hauteur = i.getImage().getHeight();
        zoneCollision = new Rectangle2D(largeur/2,hauteur/2,largeur,hauteur);
    }

    public Rectangle2D getZoneCollision() {
        return zoneCollision;
    }

    public void setZoneCollision(Rectangle2D zoneCollision) {
        this.zoneCollision = zoneCollision;
    }
}
