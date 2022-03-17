package com.mauja.maujaadventures.entites;


import com.mauja.maujaadventures.annotations.Param;
import com.mauja.maujaadventures.logique.*;

/**
 * Personnage jouable jouer par l'utilisateur possédant un état et les attributs d'un vivant
 */
public class PersonnageJouable extends Vivant {
    private EtatAction etatAction;

    /**
     * Constructeur du personnage jouable
     * @param position position x et y du personnage Jouable
     * @param dimension taille et hauteur du personnage Jouable
     * @param collision  Rectangle de la collision du personnage Jouable
     * @param velocite vitesse du personnageJouable
     * @param attaque Attaque du personnage Jouable
     * @param pointsDeVie Erreur de passage d'arguments
     * @throws IllegalArgumentException Erreur de passage d'arguments
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public PersonnageJouable(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                             @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                             @Param(nom = "attaque", classe = Attaque.class) Attaque attaque,
                             @Param(nom = "pv", classe = int.class) int pointsDeVie) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque, pointsDeVie);
        etatAction = EtatAction.SANS_ACTION;
    }

    /**
     * Constructeur Annotation du PersonnageJouable
     * @param position position x et y du personnage Jouable
     * @param dimension taille et hauteur du personnage Jouable
     * @param collision  Rectangle de la collision du personnage Jouable
     * @param velocite vitesse du personnageJouable
     * @param attaque Attaque du personnage Jouable
     * @throws IllegalArgumentException Erreur de passage d'arguments
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public PersonnageJouable(@Param(nom = "position", classe = Position.class) Position position,
                             @Param(nom = "dimension", classe = Dimension.class) Dimension dimension,
                             @Param(nom = "collision", classe = Rectangle.class) Rectangle collision,
                             @Param(nom = "velocite", classe = Velocite.class) Velocite velocite,
                             @Param(nom = "attaque", classe = Attaque.class) Attaque attaque) throws IllegalArgumentException {
        super(position, dimension, collision, velocite, attaque);
        etatAction = EtatAction.SANS_ACTION;
    }

    /**
     * Récupération de l'état de l'état
     * @return état actuelle de l'ennemi
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public EtatAction getEtatAction() {
        return etatAction;
    }

    /**
     * Modification de l'état du jouable
     * @param etatAction Nouvelle état du personnageJouable
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setEtatAction(EtatAction etatAction) {
        this.etatAction = etatAction;
    }

    /**
     * Mise à jour de Personnage Jouable
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public void miseAJour() {
        //Ne fait rien.
    }
    /**
     * Valeur de hachage unique du personnageJouable
     * @return entier correspondant à la table de hachage de l'ennemi et ses attributs
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return super.hashCode() + 7 * etatAction.hashCode();
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
        PersonnageJouable personnage = (PersonnageJouable) obj;
        return equals(personnage);
    }
    /**
     * Comparaison du personnage Jouable
     * @param personnage Personnage jouable que l'on souhaite comparée
     * @return true si égal sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(PersonnageJouable personnage) {
        return super.equals(personnage)
                && etatAction.equals(personnage.getEtatAction());
    }
    /**
     * Redéfinition de l'affichage console de l'ennemi
     * @return chaîne de caractère de son affichage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString()
                + "\nEtat d'action : " + etatAction;
    }
}
