package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

public class Levier extends ElementInteractif{
    private static final double LARGEUR_DEFAUT = 15;
    private static final double HAUTEUR_DEFAUT = 15;
    private boolean active;

    @ConstructeurXml
    public Levier(@Param(nom = "x") Double x,
                  @Param(nom = "y")Double y,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        super(new Position(x,y), new Rectangle(10,10,10,10));
        this.active = active;
    }

    public Levier(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        super(position, collision);
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

    public class Builder{
        public Builder() {
            active=false;
        }
    }
}
