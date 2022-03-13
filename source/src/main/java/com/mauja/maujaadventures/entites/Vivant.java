package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.*;

public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    protected Attaque attaque;
    protected int pointsDeVie;

    public Vivant(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                  @Param(nom = "attaque", classe = Attaque.class) Attaque attaque,
                  @Param(nom = "pv", classe = int.class) int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);

        this.attaque = attaque == null ? new Attaque(null, 0) : attaque;
        this.pointsDeVie = pointsDeVie <= 0 ? POINTS_DE_VIE_PAR_DEFAUT : pointsDeVie;
    }

    public Vivant(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                  @Param(nom = "attaque", classe = Attaque.class) Attaque attaque) throws IllegalArgumentException {
        this(position, dimension, collision, velocite, attaque, POINTS_DE_VIE_PAR_DEFAUT);
    }

    public Attaque getAttaque() {
        return attaque;
    }

    public void setAttaque(Attaque attaque) {
        this.attaque = attaque;
    }

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public void setPointsDeVie(int pointsDeVie) {
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
