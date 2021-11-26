package com.mauja.maujaadventures.modele.action.deplace;

import com.mauja.maujaadventures.modele.Entite;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class DeplaceurEntite implements Deplaceur {
    /**
     * Méthode permettant le déplacement de l'entite en la modifiant avec son setter
     * @param e Correspond à l'entite que l'on va déplacer
     * @param x Correspond à la position X de l'entite
     * @param y Correspond à la position Y de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void deplace(Entite e, double x, double y) {
        /*Rectangle2D oldCollisions = e.getCollision().getZoneCollision();
        Rectangle2D newCollisions = new Rectangle2D(x,y,oldCollisions.getWidth(),oldCollisions.getHeight());
        for (Rectangle2D zone : l){
            if(zone.intersects(newCollisions))
                return;
        }*/

        e.getPosition().setPositionX(x);
        e.getPosition().setPositionY(y);
        //e.getCollision().setZoneCollision(newCollisions);
    }
}
