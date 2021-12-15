package com.mauja.maujaadventures.modele.monde;


import com.mauja.maujaadventures.modele.Rectangle;
import com.mauja.maujaadventures.modele.Dimension;

import java.util.Objects;

public abstract class Tuile {
    private int id;
    private final String identifiantJeuDeTuile;
    private Rectangle collision;
    protected static Dimension dimension;

    /**
     * Constructeur de la classe abstraite Tuile
     * @param id Id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Tuile(int id, String identifiantJeuDeTuile, Rectangle collision) {
        dimension = new Dimension(32, 32);
        this.id = id;
        this.collision = collision;
        this.identifiantJeuDeTuile = identifiantJeuDeTuile;
    }

    /**
     * Getter de la dimension
     * @return Dimension de la tuile (Hauteur, Largeur)
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimension() { return dimension; }

    /**
     * Setter de la dimension de la tuile
     * @param dimensions Nouvelle dimensions de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setDimension(Dimension dimensions) { Tuile.dimension = dimensions; }

    public String getIdentifiantJeuDeTuile() {
        return identifiantJeuDeTuile;
    }


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
        Tuile autre = (Tuile) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param t Tuile que l'on veut comparer
     * @return True si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Tuile t) {
        return t.getId() == id && Objects.equals(t.getIdentifiantJeuDeTuile(), identifiantJeuDeTuile);
    }

    /**
     * Redéfinition du toString
     * @return Chaîne de caractère que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString() + ", Id :" + id
                + ", dimension :  " + dimension.toString() +
                "collision : " + collision.toString()
                + "Id du jeu de tuile " + identifiantJeuDeTuile;
    }
}
