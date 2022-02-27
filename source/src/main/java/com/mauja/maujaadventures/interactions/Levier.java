package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.ConstructDef;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

public class Levier extends ElementInteractif{
    private static final double LARGEUR_DEFAUT = 15;
    private static final double HAUTEUR_DEFAUT = 15;
    private Position position;
    private boolean active;

    @ConstructDef
    public Levier(@Param(nom = "x") Double x, @Param(nom = "y") Double y,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        this.position = new Position(x,y);
        this.active = active;
    }

    public static double getLargeurDefaut() {
        return LARGEUR_DEFAUT;
    }

    public static double getHauteurDefaut() {
        return HAUTEUR_DEFAUT;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Position getPosition() {
        return position;
    }

    public Rectangle getCollision(){
        return new Rectangle( position , new Dimension(LARGEUR_DEFAUT , HAUTEUR_DEFAUT ));
    }
}
