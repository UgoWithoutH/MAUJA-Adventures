package com.mauja.maujaadventures.modele.action.deplace;

import com.mauja.maujaadventures.modele.Entite;
import javafx.geometry.Rectangle2D;

import java.util.List;

public class DeplaceurEntite implements IDeplaceur {
    @Override
    public void deplaceur(Entite e, int x, int y, List<Rectangle2D> l) {
        Rectangle2D oldCollisions = e.getCollision().getZoneCollision();
        Rectangle2D newCollisions = new Rectangle2D(x,y,oldCollisions.getWidth(),oldCollisions.getHeight());
        for (Rectangle2D zone : l){
            if(zone.intersects(newCollisions))
                return;
        }

        e.getPosition().setPositionX(x);
        e.getPosition().setPositionY(y);
        e.getCollision().setZoneCollision(newCollisions);
    }
}
