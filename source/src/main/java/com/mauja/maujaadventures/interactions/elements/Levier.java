package com.mauja.maujaadventures.interactions.elements;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;

public class Levier extends ElementInteractif {
    private static final int NOMBRE_ETATS_PAR_DEFAUT = 2;
    private boolean active;
    private int nombreEtats;
    private int etatCourant;

    @ConstructeurXml
    public Levier(@Param(nom = "position", classe = Position.class, estPrimitif = false) Position position,
                  @Param(nom = "collision", classe = Rectangle.class, estPrimitif = false) Rectangle collision,
                  @Param(nom = "active", classe = Boolean.class) boolean active,
                  @Param(nom = "nombreEtats", classe = Integer.class) int nombreEtats) {
        super(position, collision);
        this.active = active;
        this.nombreEtats = nombreEtats;
        etatCourant = 0;
    }

    public Levier(@Param(nom = "position", classe = Position.class, estPrimitif = false) Position position,
                  @Param(nom = "collision", classe = Rectangle.class, estPrimitif = false) Rectangle collision,
                  @Param(nom = "active", classe = Boolean.class) boolean active) {
        this(position, collision, active, NOMBRE_ETATS_PAR_DEFAUT);
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getNombreEtats() {
        return nombreEtats;
    }

    public void setEtatCourant(int etatCourant) {
        this.etatCourant = etatCourant;
    }

    public int getEtatCourant() {
        return etatCourant;
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
