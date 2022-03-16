package com.mauja.maujaadventures.entites;

import com.mauja.maujaadventures.annotations.ConstructeurXml;
import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.comportements.Comportement;
import com.mauja.maujaadventures.comportements.ComportementNull;
import com.mauja.maujaadventures.logique.*;

public class Ennemi extends Vivant {
    private Comportement comportement;

    /**
     * Constructeur annotation de l'ennmi
     * @param xEn Position x de l'ennemi
     * @param yEn Position y de l'ennemi
     * @param hautEn dimension de l'ennemi en hauteur
     * @param largEn dimension de l'ennemi en largeur
     * @param xCol collision de l'ennemi en x
     * @param yCol collision de l'ennemi en y
     * @param largCol largeur de la collision
     * @param hautCol hauteur de la collision
     * @param pointsDeVie nombre de point de vie de l'ennemi
     * @throws IllegalArgumentException Argument envoyé non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @ConstructeurXml
    public Ennemi(@Param(nom = "xEn") Double xEn, @Param(nom = "yEn") Double yEn, @Param(nom = "hautEn")Double hautEn,
                  @Param(nom = "largEn")Double largEn, @Param(nom = "xCol")Double xCol, @Param(nom = "yCol")Double yCol,
                  @Param(nom = "largCol") Double largCol, @Param(nom = "hautCol") Double hautCol,
                  @Param(nom = "pointsDeVie", classe = Integer.class) int pointsDeVie) throws IllegalArgumentException {
        super(new Position(xEn, yEn), new Dimension(largEn, hautEn),
                new Rectangle(xCol, yCol, largCol, hautCol), new Velocite(), null,
                pointsDeVie);
        this.comportement = comportement == null ? new ComportementNull() : comportement;
    }

    /**
     * Ennemi
     * @param position position de l'ennemi sur la carte
     * @param dimension taille de l'ennmi
     * @param collision rectangle de la collision de l'ennmi
     * @param velocite vitesse de l'ennmi
     * @param attaque nombre de dégât réalisé par l'ennemi
     * @param comportement comportement de l'ennemi
     * @param pointsDeVie vie de l'ennmi
     * @throws IllegalArgumentException Argument envoyé non voulu
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, Comportement comportement, int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
        this.comportement = comportement == null ? new ComportementNull() : comportement;
    }

    /**
     * Constructeur Ennemi
     * @param position position de l'ennemi sur la carte
     * @param dimension taille de l'ennmi
     * @param collision rectangle de la collision de l'ennmi
     * @param velocite vitesse de l'ennmi
     * @param attaque nombre de dégât réalisé par l'ennemi
     * @param comportement comportement de l'ennemi
     * @throws IllegalArgumentException Argument envoyé non voulu de l'ennemi
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Ennemi(Position position, Dimension dimension, Rectangle collision, Velocite velocite,
                  Attaque attaque, Comportement comportement) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
        this.comportement = comportement == null ? new ComportementNull() : comportement;
    }

    /**
     * Récupération du comportement
     * @return comportement actuel du joueur
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Comportement getComportement() {
        return comportement;
    }

    /**
     * Modification du comportement
     * @param comportement Nouveau comportement de l'ennemi
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setComportement(Comportement comportement) {
        this.comportement = comportement;
    }

    /**
     * Mise A jour de l'agissement du comportement de l'ennemi
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void miseAJour() {
        comportement.agit(this, 0);
    }

    /**
     * Valeur de hachage unique de l'ennmi
     * @return entier correspondant à la table de hachage de l'ennemi et ses attributs
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return super.hashCode() + comportement.hashCode();
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
        Ennemi ennemi = (Ennemi) obj;
        return equals(ennemi);
    }

    /**
     * Comparaison de l'ennemi
     * @param ennemi ennemi que l'on souhaite comparée
     * @return true si égal sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Ennemi ennemi) {
        return super.equals(ennemi)
                && comportement.equals(ennemi.getComportement());
    }

    /**
     * Redéfinition de l'affichage console de l'ennemi
     * @return chaîne de caractère de son affichage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nComportement : " + comportement;
    }
}
