package com.mauja.maujaadventures.logique;

import java.util.Objects;

public class Velocite {
    private double x;
    private double y;
    public static final double VELOCITE_PAR_DEFAUT = 3;

    public Velocite(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Velocite() {
        this(VELOCITE_PAR_DEFAUT, VELOCITE_PAR_DEFAUT);
    }

    public double getX() {
        return x;
    }

    private void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    private void setY(double y) {
        this.y = y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Velocite velocite = (Velocite) obj;
        return equals(velocite);
    }

    public boolean equals(Velocite velocite) {
        return x == velocite.getX()
                && y == velocite.getY();
    }

    @Override
    public String toString() {
        return "[" + x + "v, " + y + "]";
    }
}
