package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.Collision;
import com.mauja.maujaadventures.modele.action.affiche.Affichable;
import com.mauja.maujaadventures.modele.Dimension;

public class Tuile extends Affichable {
    public static final int LARGEUR_TUILE = 32;
    public static final int HAUTEUR_TUILE = 32;

    private int id;
    private String identifiantJeuDeTuile;
    private Collision collision;
    protected static Dimension dimension;

    /**
     * Constructeur de la classe abstraite Tuile
     * @param id Id de la tuile
     * @param chemin Chemin de l'image de la tuile
     */
    public Tuile(int id, String identifiantJeuDeTuile, Collision collision, String chemin) {
        super(chemin);
        dimension = new Dimension(LARGEUR_TUILE, HAUTEUR_TUILE);
        this.id = id;
        this.identifiantJeuDeTuile = identifiantJeuDeTuile;
    }

    /**
     * Getter de la dimension
     * @return Dimension de la tuile (Hauteur, Largeur)
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimensions() { return dimension; }

    /**
     * Setter de la dimension de la tuile
     * @param dimensions Nouvelle dimensions de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setDimensions(Dimension dimensions) { Tuile.dimension = dimensions; }

    public String getIdentifiantJeuDeTuile() {
        return identifiantJeuDeTuile;
    }

    private void setIdentifiantJeuDeTuile(String identifiantJeuDeTuile) {
        this.identifiantJeuDeTuile = identifiantJeuDeTuile;
    }

    public Collision getCollision() {
        return collision;
    }

    private void setCollision(Collision collision) {
        this.collision = collision;
    }

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

    @Override
    public int hashCode() { return id+31* dimension.hashCode(); }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Tuile autre = (Tuile) obj;
        return equals(autre);
    }

    public boolean equals(Tuile t) {
        boolean resultat = (t.getId() == id)
                && (dimension.equals(t.getDimensions()));
        return resultat;
    }
}
