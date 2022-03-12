package com.mauja.maujaadventures.interactions;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

public class Levier extends ElementInteractif {
    private static double LARGEUR_DEFAUT;
    private static double HAUTEUR_DEFAUT;
    private boolean active;


    public Levier(@Param(nom = "x") Double x,
                  @Param(nom = "y")Double y,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        super(new Position(x,y), new Rectangle(x,y,LARGEUR_DEFAUT,HAUTEUR_DEFAUT));
        this.active = active;
    }

    @ConstructeurXml
    public Levier(@Param(nom = "position", classe = Position.class, estPrimitif = false) Position position,
                  @Param(nom = "collision", classe = Rectangle.class, estPrimitif = false) Rectangle collision,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        super(position, collision);
        this.active = active;
    }

    public static void setHauteurDefaut(double hauteurDefaut) {
        HAUTEUR_DEFAUT = hauteurDefaut;
    }

    public static void setLargeurDefaut(double largeurDefaut) {
        LARGEUR_DEFAUT = largeurDefaut;
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

    @Override
    public void miseAJour() {
        //Ne fait rien.
    }

    public class Builder{

        public Builder() {
            //active=false;
        }
    }
}
