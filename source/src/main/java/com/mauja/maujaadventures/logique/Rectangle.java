package com.mauja.maujaadventures.logique;

public class Rectangle {
    private Dimension dimension;
    private Position position;

    public Rectangle(double positionX, double positionY, double largeur, double hauteur)
            throws IllegalArgumentException {
        if (largeur < 0 || hauteur < 0) {
            throw new IllegalArgumentException("La largeur ou la hauteur d'un rectangle ne peut pas être négative. "
                    + "Donné : " + largeur + "x" + hauteur + ".");
        }
        dimension = new Dimension(largeur, hauteur);
        position = new Position(positionX, positionY);
    }

    /**
     * Constructeur de collision
     * @param position Position de la collision
     */
    public Rectangle(Position position, Dimension dimension) throws IllegalArgumentException {
        if (position == null || dimension == null) {
            throw new IllegalArgumentException("La position ou la dimension donnée en paramètre ne peuvent "
                    + "pas être nulles. Position : " + position + ", dimension : " + dimension);
        }
        this.position = position;
        this.dimension = dimension;
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

    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    private void setPosition(Position position) {
        this.position = position;
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
}
