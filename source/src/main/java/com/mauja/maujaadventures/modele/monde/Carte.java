package com.mauja.maujaadventures.modele.monde;

public class Carte
{
    private String nom;
    private String id;
    private int largeur;
    private int hauteur;

    public Carte(String nom, String id, int largeur, int hauteur){
        this.setNom(nom);
        this.setId(id);
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
