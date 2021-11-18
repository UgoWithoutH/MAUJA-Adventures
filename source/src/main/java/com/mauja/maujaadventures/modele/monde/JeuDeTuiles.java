package com.mauja.maujaadventures.modele.monde;
//import javafx.scene.shape.*;

import java.util.ArrayList;

public class JeuDeTuiles {
    private String image;
    private int nombreTuiles;
    private int largeur;
    private int hauteur;
    private ArrayList<Tuile> listeDeTuiles;
    //private Rectangle lesCollisions; //Je ne vois pour l'instant pas l'utilité de cet attribut je laisse donc en commentaire

    public JeuDeTuiles(String image, int largeur, int hauteur){
        setImage(image);
        setLargeur(largeur);
        setHauteur(hauteur);
        listeDeTuiles= new ArrayList<Tuile>();
        nombreTuiles=listeDeTuiles.size();
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public int getNombreTuiles() {
        return nombreTuiles;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /*public Rectangle getLesCollisions() {
        return lesCollisions;
    }

    public void setLesCollisions(Rectangle lesCollisions) {
        this.lesCollisions = lesCollisions;
    }*/
}
