package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.logique.Dimension;
import com.mauja.maujaadventures.logique.Position;
import com.mauja.maujaadventures.logique.Rectangle;
import com.mauja.maujaadventures.logique.Velocite;

public abstract class Entite extends ElementInteractif {
    protected Dimension dimension;
    protected Velocite velocite;
    protected Direction direction;

    /**
     * Constructeur de la classe Abstraite
     * @param position Position de l'entite
     * @param collision Collision que l'entite va posséder
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Entite(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "velocite", classe = Velocite.class) Velocite velocite)
            throws IllegalArgumentException {
        super(position, collision);
        verificationParametre(dimension, "dimension");

        if (velocite == null) {
            velocite = new Velocite();
        }

        this.dimension = dimension;
        this.velocite = velocite;
        direction = Direction.BAS;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de Entite
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 7 * (velocite.hashCode()
                + dimension.hashCode()
                + direction.hashCode());
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
        return super.equals(entite)
                && dimension.equals(entite.getDimension())
                && velocite.equals(entite.getVelocite())
                && direction.equals(entite.getDirection());
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nDimensions : " + dimension.toString()
                + "\nVelocite : " + velocite
                + "\nDirection : " + direction;
    }
}
