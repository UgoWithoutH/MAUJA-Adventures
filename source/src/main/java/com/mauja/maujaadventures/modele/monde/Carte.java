package com.mauja.maujaadventures.modele.monde;

import java.util.ArrayList;

public class Carte
{
    private String nom;
    private String id;
    private ArrayList<Calque> listeDeCalques;
    private int largeur;
    private int hauteur;

    public Carte(String nom, String id, int largeur, int hauteur){
        this.setNom(nom);
        this.setId(id);
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.listeDeCalques= new ArrayList<Calque>();
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

    public ArrayList<Calque> getListeDeCalques() {
        return listeDeCalques;
    }

    public void ajouterCalques(Calque c){
        this.listeDeCalques.add(c);
    }

    private void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    private void setId(String id) {
        this.id = id;
    }

    private void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    private void setNom(String nom) {
        this.nom = nom;
    }
}
