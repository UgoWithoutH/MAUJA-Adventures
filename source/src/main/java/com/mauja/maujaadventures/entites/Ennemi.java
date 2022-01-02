package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.comportements.Comportement;
import com.mauja.maujaadventures.comportements.ComportementNull;
import com.mauja.maujaadventures.logique.*;

public abstract class Ennemi extends Vivant {
    private Comportement comportement;

    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, Comportement comportement, int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
        if (comportement == null) {
            comportement = new ComportementNull();
        }
        this.comportement = comportement;
    }

    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, Comportement comportement) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
        if (comportement == null) {
            comportement = new ComportementNull();
        }
        this.comportement = comportement;
    }

    public Comportement getComportement() {
        return comportement;
    }

    @Override
    public int hashCode() {
        return super.hashCode() + comportement.hashCode();
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
        return super.equals(ennemi)
                && comportement.equals(ennemi.getComportement());
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nComportement : " + comportement;
    }
}
