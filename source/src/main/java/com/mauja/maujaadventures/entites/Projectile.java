package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.Velocite;

import java.util.Objects;

public class Projectile extends Entite {
    private int degats;

    public Projectile(@Param(nom = "position", classe = Position.class) Position position,
                      @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                      @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                      @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                      @Param(nom = "degats", classe = int.class) int degats) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);
        if (degats < 0) {
            degats = 0;
        }
        this.degats = degats;
    }

    public int getDegats() {
        return degats;
    }

    private void setDegats(int degats) {
        this.degats = degats;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(degats);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Projectile projectile = (Projectile) obj;
        return equals(projectile);
    }

    public boolean equals(Projectile projectile) {
        return super.equals(projectile)
                && degats == projectile.getDegats();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nDegats : " + degats + "\u2665";
    }
}
