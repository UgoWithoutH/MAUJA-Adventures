package com.mauja.maujaadventures.modele.personnage;


import javafx.scene.image.Image;

public class ProprietesImage {
    private String cheminImage;
    private double longueur;
    private double hauteur;
    private Image image;

    public ProprietesImage(String cheminImage) {
        this.cheminImage = cheminImage;
        try {
            image = new Image(cheminImage);
            hauteur = image.getHeight();
            longueur = image.getWidth();
        }
        catch(IllegalArgumentException i){
            System.out.println("L'image n'a pas été trouvée. Chemin: " + getCheminImage());
        }
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = this.image;
    }

    public String getCheminImage() {
        return cheminImage;
    }

    public void setCheminImage(String cheminImage) {
        this.cheminImage = cheminImage;
    }

    public double getLongueur() {
        return longueur;
    }

    public void setLongueur(double longueur) {
        this.longueur = longueur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }
}
