package com.mauja.maujaadventures.modele.monde;

import java.util.ArrayList;

public class Calque {

    private int hauteur;
    private int largeur;

    private ArrayList<Tuile> listeDeTuiles;

    public Calque(int largeur, int hauteur) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.listeDeTuiles = new ArrayList<Tuile>();
    }

    public ArrayList<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void ajouterTuile(Tuile t){
        this.listeDeTuiles.add(t);
    }
}
