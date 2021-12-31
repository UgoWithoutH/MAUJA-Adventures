package com.mauja.maujaadventures.modele.entites;

import com.mauja.maujaadventures.modele.logique.Dimension;
import com.mauja.maujaadventures.modele.logique.Position;
import com.mauja.maujaadventures.modele.logique.Rectangle;
import com.mauja.maujaadventures.modele.logique.Velocite;

import java.util.Objects;

public class Projectile extends Entite {
    private int degats;
    private Direction direction;

    public Projectile(Position position, Dimension dimension, Rectangle collision, Velocite velocite, int degats,
                      Direction direction) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);
        if (degats < 0) {
            degats = 0;
        }
        this.degats = degats;
        this.direction = direction;
    }

    public int getDegats() {
        return degats;
    }

    private void setDegats(int degats) {
        this.degats = degats;
    }

    public Direction getDirection() {
        return direction;
    }

    private void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + Objects.hash(direction, degats);
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
                && direction.equals(projectile.getDirection())
                && degats == projectile.getDegats();
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nDegats : " + degats + "\u2665"
                + "\nDirection : " + direction;
    }
}
