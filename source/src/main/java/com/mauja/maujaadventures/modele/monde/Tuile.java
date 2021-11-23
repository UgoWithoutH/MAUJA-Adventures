package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.Affichable;
import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.personnage.ProprietesImage;

public abstract class Tuile extends Affichable {
    public static final int LARGEUR_TUILE = 32;
    public static final int HAUTEUR_TUILE = 32;

    private int id;
    protected static Dimension dimensions;

    public Tuile(int id, String chemin) {
        super(chemin);
        dimensions = new Dimension(LARGEUR_TUILE, HAUTEUR_TUILE);
        this.setId(id);
    }

    public Dimension getDimensions() { return dimensions; }

    public void setDimensions(Dimension dimensions) { Tuile.dimensions = dimensions; }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }
}
