package com.mauja.maujaadventures.modele;

public abstract class Collision {
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

    public abstract Object getZoneCollision();
}
