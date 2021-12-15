package com.mauja.maujaadventures.modele.personnage;

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
}
