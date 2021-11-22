package com.mauja.maujaadventures.modele.monde;


public abstract class Tuile {
    private int id;
    protected static int longueur;
    protected static int largeur;
    private ImageSource image;

    public Tuile(int id, String chemin) {
        longueur=32;
        largeur=32;
        this.setId(id);
        this.setImage(new ImageSource(chemin));
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public static int getLongueur() {
        return longueur;
    }

    public static int getLargeur() {
        return largeur;
    }

    public ImageSource getImage() {
        return image;
    }

    private void setImage(ImageSource image) {
        this.image = image;
    }



}
