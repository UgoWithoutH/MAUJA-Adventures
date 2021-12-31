package com.mauja.maujaadventures.modele.logique;

public class Rectangle {
    private Dimension dimension;
    private Position position;

    /**
     * Constructeur de collision
     * @param position Position de la collision
     */
    public Rectangle(Position position, Dimension dimension){
        this.position = position;
        this.dimension = dimension;
    }

    /**
     * Getter de la posion
     * @return Position de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() { return position; }

    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return dimension.toString() + " " + position.toString();
    }

    /**
     * Redéfinition du hashCOde
     * @return Entier de l'hachage des attributs de Collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return dimension.hashCode() + 31 * position.hashCode();
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
        Rectangle autre = (Rectangle) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param c Collision que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Rectangle c) {
        return dimension.equals(c.getDimension()) && position.equals(c.getPosition());
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
