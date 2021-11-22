package com.mauja.maujaadventures.modele;

public class Dimension {
    private int largeur;
    private int hauteur;


    public Dimension(int largeur, int hauteur) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }


    public int getLargeur() {
        return largeur;
    }

    private void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    private void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }


}
