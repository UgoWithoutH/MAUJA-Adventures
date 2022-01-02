package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.logique.*;

public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    private Attaque attaque;
    private int pointsDeVie;

    public Vivant(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);

        if (attaque == null) {
            attaque = new Attaque(null, 0);
        }
        this.attaque = attaque;
        if (pointsDeVie <= 0) {
            pointsDeVie = POINTS_DE_VIE_PAR_DEFAUT;
        }
        this.pointsDeVie = pointsDeVie;
    }

    public Vivant(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque) throws IllegalArgumentException {
        this(position, dimension, collision, velocite, attaque, POINTS_DE_VIE_PAR_DEFAUT);
    }

    public Attaque getAttaque() {
        return attaque;
    }

    private void setAttaque(Attaque attaque) {
        this.attaque = attaque;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    private void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }

    @Override
    public int hashCode() {
        return 7 * (super.hashCode()
                + attaque.hashCode());
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Vivant vivant = (Vivant) obj;
        return equals(vivant);
    }

    public boolean equals(Vivant vivant) {
        return super.equals(vivant)
                && attaque.equals(vivant.getAttaque());
    }

    @Override
    public String toString() {
        return super.toString()
                + "\nAttaque : " + attaque.toString()
                + "\nPV : " + pointsDeVie + "\u2665";
    }
}
