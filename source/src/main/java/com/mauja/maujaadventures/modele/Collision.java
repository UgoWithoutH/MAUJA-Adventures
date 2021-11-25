package com.mauja.maujaadventures.modele;

public class Collision {
    private double largeur;
    private double hauteur;
    private Position position;

    public Collision(Position position, double largeur, double hauteur){
        this.position = position;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    public double getLargeur() { return largeur; }

    private void setLargeur(double largeur) { this.largeur = largeur; }

    public double getHauteur() { return hauteur; }

    private void setHauteur(double hauteur) { this.hauteur = hauteur; }

    public Position getPosition() { return position; }

    private void setPosition(Position position) { this.position = position; }

    @Override
    public String toString() {
        return "L : " + largeur
                + ", H : " + hauteur
                + ", Position : " + position.toString();
    }

    @Override
    public int hashCode() {
        return (int)(hauteur+largeur)+ 31*position.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Collision autre = (Collision) obj;
        return equals(autre);
    }

    public boolean equals(Collision c) {
        boolean resultat= (c.getHauteur() == hauteur) && (c.getLargeur() == largeur ) && ( position.equals(c.getPosition()));
        return resultat ;
    }
}
