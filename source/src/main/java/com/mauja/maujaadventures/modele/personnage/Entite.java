package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.Position;
import com.mauja.maujaadventures.modele.Rectangle;

public abstract class Entite {
    private Position position;
    private Rectangle rectangle;
    private Dimension dimension;

    /**
     * Constructeur de la classe Abstraite
     * @param position Position de l'entite
     * @param rectangle Collision que l'entite va posséder
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Entite(Position position, Rectangle rectangle){
        this.position = position;
        this.rectangle = rectangle;
        this.dimension = new Dimension(32, 32);
    }
    /**
     * Getter de la position
     * @return Position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() { return position; }
    /**
     * Setter de la position
     * @param position Nouvelle position de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPosition(Position position) { this.position = position; }
    /**
     * Getter de la collision
     * @return La zone de collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Rectangle getCollision() { return rectangle; }
    /**
     * Setter de la collision
     * @param rectangle Nouvelle collision de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setCollision(Rectangle rectangle) { this.rectangle = rectangle; }
    /**
     * Getter de la dimension
     * @return La dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension getDimension() { return dimension;}
    /**
     * Setter de la dimension
     * @param dimension Nouvelle dimension (Hauteur et Largeur) de l'entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setDimension(Dimension dimension) { this.dimension = dimension; }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Position : " + position.toString() + ", "
                + "Dimensions : " + dimension.toString() + ", "
                + "Collision : " + rectangle.toString();
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 31*position.hashCode()+31* dimension.hashCode()+31* rectangle.hashCode();
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
        Entite autre = (Entite) obj;
        return equals(autre);
    }
    /**
     * Méthode equals
     * @param e Entité que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Entite e) {
        boolean resultat = (position.equals(e.getPosition())) && (position.equals(e.getCollision())) &&
                (dimension.equals(e.getDimension()));
        return resultat;
    }
}
