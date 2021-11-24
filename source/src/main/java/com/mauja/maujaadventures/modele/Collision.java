package com.mauja.maujaadventures.modele;

import com.mauja.maujaadventures.modele.personnage.ProprietesImage;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;

public class Collision {
    private Rectangle2D zoneCollision;
    /**
     * Constructeur de la classe
     * @param r Rectangle utiliser lors de la collsions
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Collision(ProprietesImage i){
        double largeur = i.getImage().getWidth();
        double hauteur = i.getImage().getHeight();
        zoneCollision = new Rectangle2D(largeur/2,hauteur/2,largeur,hauteur);
    }
    /**
     * getter de la zone de collision
     * @return La zone où on aura une collisions
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Rectangle2D getZoneCollision() {
        return zoneCollision;
    }
    /**
     * Setter de la collisions
     * @param zoneCollision La nouvelle zone de collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setZoneCollision(Rectangle2D zoneCollision) {
        this.zoneCollision = zoneCollision;
    }
}
