package com.mauja.maujaadventures.modele.entites;

import com.mauja.maujaadventures.modele.logique.*;

public abstract class Ennemi extends Vivant {

    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
    }

    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Ennemi ennemi = (Ennemi) obj;
        return equals(ennemi);
    }

    public boolean equals(Ennemi ennemi) {
        return super.equals(ennemi);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
