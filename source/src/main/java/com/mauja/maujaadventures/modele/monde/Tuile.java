package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.personnage.ImageSource;

public abstract class Tuile {
    public static final int LARGEUR_TUILE = 32;
    public static final int HAUTEUR_TUILE = 32;

    private int id;
    protected static Dimension dimensions;
    private ImageSource image;

    public Tuile(int id, String chemin) {
        dimensions = new Dimension(LARGEUR_TUILE, HAUTEUR_TUILE);
        this.setId(id);
        this.setImage(new ImageSource(chemin));
    }

    public Dimension getDimensions() { return dimensions; }

    public void setDimensions(Dimension dimensions) { Tuile.dimensions = dimensions; }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public ImageSource getImage() {
        return image;
    }

    private void setImage(ImageSource image) {
        this.image = image;
    }
}
