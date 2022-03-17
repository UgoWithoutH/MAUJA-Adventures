package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.*;

/**
 * Classe d'un vivant possédant une attaque des points de vie ainsi qu'une collision, une vitesse et un nombre de dégâts d'attaque
 * ainsi qu'une position
 */
public abstract class Vivant extends Entite {
    private static final int POINTS_DE_VIE_PAR_DEFAUT = 10;
    protected Attaque attaque;
    protected int pointsDeVie;

    /**
     * Constructeur du vivant
     * @param position position X et Y du vivant
     * @param dimension Taille et hauteur de l'ennemi
     * @param collision Collision du vivant
     * @param velocite vitesse du vivant
     * @param attaque Attaque de l'ennemi
     * @param pointsDeVie nombre de point de vie de l'ennemi
     * @throws IllegalArgumentException Argument envoyé non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Vivant(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                  @Param(nom = "attaque", classe = Attaque.class) Attaque attaque,
                  @Param(nom = "pv", classe = int.class) int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite);

        this.attaque = attaque == null ? new Attaque(null, 0) : attaque;
        this.pointsDeVie = pointsDeVie <= 0 ? POINTS_DE_VIE_PAR_DEFAUT : pointsDeVie;
    }

    /**
     * Constructeur du vivant
     * @param position position X et Y du vivant
     * @param dimension Taille et hauteur de l'ennemi
     * @param collision Collision du vivant
     * @param velocite vitesse du vivant
     * @param attaque Attaque de l'ennemi
     * @throws IllegalArgumentException Argument envoyé non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Vivant(@Param(nom = "position", classe = Position.class) Position position,
                  @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                  @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                  @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                  @Param(nom = "attaque", classe = Attaque.class) Attaque attaque) throws IllegalArgumentException {
        this(position, dimension, collision, velocite, attaque, POINTS_DE_VIE_PAR_DEFAUT);
    }

    /**
     * Récupération de l'attaque
     * @return Attaque du vivant
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Attaque getAttaque() {
        return attaque;
    }

    /**
     * Modification de l'attaque de l'ennemi
     * @param attaque nouvel attaque du vivant
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setAttaque(Attaque attaque) {
        this.attaque = attaque;
    }

    /**
     * Récupération du nombre point de vie
     * @return Nombre de point de vie actuel du vivant
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * Modification du nombre point de vie
     * @param pointsDeVie Nouvelle valeur du point de vie
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setPointsDeVie(int pointsDeVie) {
        this.pointsDeVie = pointsDeVie;
    }
    /**
     * Valeur de hachage unique du vivant
     * @return entier correspondant à la table de hachage de l'ennemi et ses attributs
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return 7 * (super.hashCode()
                + attaque.hashCode());
    }
    /**
     * Redéfinition de la comparaison de l'ennemi avec un autre objet
     * @param obj Objet à comparer
     * @return true si l'ennemi est égal a l'objet sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Vivant vivant = (Vivant) obj;
        return equals(vivant);
    }
    /**
     * Comparaison du vivant
     * @param vivant vivant que l'on souhaite comparée
     * @return true si égal sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Vivant vivant) {
        return super.equals(vivant)
                && attaque.equals(vivant.getAttaque());
    }
    /**
     * Redéfinition de l'affichage console du vivant
     * @return chaîne de caractère de son affichage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nAttaque : " + attaque.toString()
                + "\nPV : " + pointsDeVie + "\u2665";
    }
}
