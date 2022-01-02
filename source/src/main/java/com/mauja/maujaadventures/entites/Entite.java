package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.Velocite;

public abstract class Entite {
    private Position position;
    private Dimension dimension;
    private Rectangle collision;
    private Velocite velocite;

    /**
     * Constructeur de la classe Abstraite
     * @param position Position de l'entite
     * @param collision Collision que l'entite va posséder
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Entite(Position position, Dimension dimension, Rectangle collision, Velocite velocite)
            throws IllegalArgumentException {
        verificationParametre(position, "position");
        verificationParametre(dimension, "dimension");

        if (velocite == null) {
            velocite = new Velocite();
        }
        this.position = position;
        this.collision = collision;
        this.dimension = dimension;
        this.velocite = velocite;
    }

    /**
     * Getter de la position
     * @return Position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Setter de la position
     * @param position Nouvelle position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPosition(Position position) {
        this.position = position;
    }

    /**
     * Getter de la collision
     * @return La zone de collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Rectangle getCollision() {
        return collision;
    }

    /**
     * Setter de la collision
     * @param rectangle Nouvelle collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setCollision(Rectangle rectangle) {
        this.collision = rectangle;
    }

    /**
     * Getter de la dimension
     * @return La dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Setter de la dimension
     * @param dimension Nouvelle dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setDimension(Dimension dimension) { this.dimension = dimension; }

    public Velocite getVelocite() {
        return velocite;
    }

    private void setVelocite(Velocite velocite) {
        this.velocite = velocite;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 7 * (position.hashCode()
                + dimension.hashCode()
                + collision.hashCode());
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Entite entite = (Entite) obj;
        return equals(entite);
    }

    /**
     * Méthode equals
     * @param entite Entité que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Entite entite) {
        return position.equals(entite.getPosition())
                && dimension.equals(entite.getDimension())
                && collision.equals(entite.getCollision())
                && velocite.equals(entite.getVelocite());
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "[" + this.getClass() + "] : " + position.toString()
                + "\nDimensions : " + dimension.toString()
                + "\nCollision : " + collision.toString()
                + "\nVelocite : " + velocite;
    }

    private void verificationParametre(Object obj, String nom) throws IllegalArgumentException {
        if (obj == null) {
            throw new IllegalArgumentException("Le paramètre " + nom + " donnée à l'entité lors de sa création "
                    + "ne peut pas être null.");
        }
    }
}
