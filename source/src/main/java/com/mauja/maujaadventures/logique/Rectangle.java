package com.mauja.maujaadventures.logique;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;

public class Rectangle {
    private Dimension dimension;
    private Position position;

    @ConstructeurXml
    public Rectangle(@Param(nom = "x") double positionX, @Param(nom = "y") double positionY,
                     @Param(nom = "largeur") double largeur, @Param(nom = "hauteur") double hauteur)
            throws IllegalArgumentException {
        verificationDimensions(largeur, hauteur);
        dimension = new Dimension(largeur, hauteur);
        position = new Position(positionX, positionY);
    }

    /**
     * Constructeur de collision
     * @param position Position de la collision
     */
    public Rectangle(@Param(nom = "position", classe = Position.class) Position position,
                     @Param(nom = "dimension", classe = Dimension.class) Dimension dimension) throws IllegalArgumentException {
        if (position == null || dimension == null) {
            throw new IllegalArgumentException("La position ou la dimension donnée en paramètre ne peuvent "
                    + "pas être nulles. Position : " + position + ", dimension : " + dimension);
        }
        verificationDimensions(dimension.getLargeur(), dimension.getHauteur());
        this.position = position;
        this.dimension = dimension;
    }

    public Rectangle(@Param(nom = "position", classe = Position.class) Position position,
                     @Param(nom = "largeur") double largeur, @Param(nom = "hauteur") double hauteur) throws IllegalArgumentException {
        this(position, new Dimension(largeur, hauteur));
    }

    public Rectangle(@Param(nom = "x") double positionX, @Param(nom = "y") double positionY,
                     @Param(nom = "dimension", classe = Dimension.class) Dimension dimension) throws IllegalArgumentException {
        this(new Position(positionX, positionY), dimension);
    }

    /**
     * Getter de la posion
     * @return Position de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() {
        return position;
    }

    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Redéfinition du hashCOde
     * @return Entier de l'hachage des attributs de Collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return dimension.hashCode() + 7 * position.hashCode();
    }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Rectangle rectangle = (Rectangle) obj;
        return equals(rectangle);
    }

    /**
     * Méthode equals
     * @param rectangle Collision que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Rectangle rectangle) {
        return dimension.equals(rectangle.getDimension())
                && position.equals(rectangle.getPosition());
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return position.toString() + " " + dimension.toString();
    }

    private void verificationDimensions(double largeur, double hauteur) throws IllegalArgumentException {
        if (largeur < 0 || hauteur < 0) {
            throw new IllegalArgumentException("La largeur ou la hauteur d'un rectangle ne peut pas être négative. "
                    + "Donné : " + largeur + "x" + hauteur + ".");
        }
    }
}
