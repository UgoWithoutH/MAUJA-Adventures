package com.mauja.maujaadventures.modele.personnage;


import javafx.scene.image.Image;

public class ImageSource {
    private String cheminImage;
    private double longueur;
    private double hauteur;
    private Image img;

    public Image getImage() {
        return img;
    }

    public void setImage(Image image) {
        this.img = img;
    }

    public ImageSource(String cheminImage) {
        this.cheminImage = cheminImage;
        try {
            img = new Image(cheminImage);
            hauteur=img.getHeight();
            longueur=img.getWidth();
        }
        catch(IllegalArgumentException i){
            System.out.println("L'image n'a pas été trouvée. Chemin: " + getCheminImage());
        }




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
