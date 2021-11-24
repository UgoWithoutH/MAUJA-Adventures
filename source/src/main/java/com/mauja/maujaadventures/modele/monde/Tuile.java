package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.action.affiche.Affichable;
import com.mauja.maujaadventures.modele.Dimension;

public abstract class Tuile extends Affichable {
    public static final int LARGEUR_TUILE = 32;
    public static final int HAUTEUR_TUILE = 32;

    private int id;
    protected static Dimension dimensions;
    /**
     * Constructeur de la classe abstraite Tuile
     * @param id Id de la tuile
     * @param chemin Chemin de l'image de la tuile
     */
    public Tuile(int id, String chemin) {
        super(chemin);
        dimensions = new Dimension(LARGEUR_TUILE, HAUTEUR_TUILE);
        this.setId(id);
    }
    /**
     * Getter de la dimension
     * @return Dimension de la tuile (Hauteur, Largeur)
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimensions() { return dimensions; }
    /**
     * Setter de la dimension de la tuile
     * @param dimensions Nouvelle dimensions de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setDimensions(Dimension dimensions) { Tuile.dimensions = dimensions; }

    /**
     * Getter de l'id de la tuile
     * @return L'Id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getId() {
        return id;
    }
    /**
     * Setter de l'id de la tuile
     * @param id Nouvelle id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setId(int id) {
        this.id = id;
    }
}
