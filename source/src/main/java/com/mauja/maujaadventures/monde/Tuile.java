package com.mauja.maujaadventures.monde;


import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.Dimension;

import java.util.Objects;

public class Tuile {
    private static final Dimension DIMENSION_PAR_DEFAUT = new Dimension(32, 32);
    public static final Tuile TUILE_IGNOREE = new Tuile(0, null, DIMENSION_PAR_DEFAUT);

    private static int nombreTuiles = 0;
    private final int id;
    private Rectangle collision;
    private Dimension dimension;

    /**
     * Constructeur de la classe abstraite Tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Tuile(int id, Rectangle collision, Dimension dimension) throws IllegalArgumentException {
        if (dimension == null || dimension.getHauteur() <= 0 || dimension.getLargeur() <= 0) {
            throw new IllegalArgumentException("La dimension de la tuile ne peut pas être nulle ou inférieure "
                    + "ou égale à zéro. Donné : " + dimension);
        }
        this.dimension = dimension;
        this.collision = collision;
        this.id = id;
        nombreTuiles++;
    }

    /**
     * Getter de la dimension
     * @return Dimension de la tuile (Hauteur, Largeur)
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimension() { return dimension; }

    public Rectangle getCollision() {
        return collision;
    }

    /**
     * Getter de l'id de la tuile
     * @return L'Id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getId() {
        return id;
    }

    public static int getNombreTuiles() {
        return nombreTuiles;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si vrai sinon faux
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Tuile tuile = (Tuile) obj;
        return equals(tuile);
    }

    /**
     * Méthode equals
     * @param tuile Tuile que l'on veut comparer
     * @return True si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Tuile tuile) {
        return tuile.getId() == id;
    }

    /**
     * Redéfinition du toString
     * @return Chaîne de caractère que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString() + ", Id :" + id
                + ", dimension :  " + dimension
                + "collision : " + collision;
    }
}
