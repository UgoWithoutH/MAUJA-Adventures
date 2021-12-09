package com.mauja.maujaadventures.modele.personnage;

import com.mauja.maujaadventures.modele.Rectangle;
import com.mauja.maujaadventures.modele.Position;

public class PersonnageJouable extends Personnage {
    private int attaque;
    /**
     * Constructeur de la classe PersonnageJouable qui instancie attaque et appelle sa classe mère Personnage pour les autres
     * paramètre
     *
     * @param position Correspond à la position du personnage Jouable
     * @param rectangle Element permettant de savoir la collision du personnage Jouable
     * @param attaque Entier de l'attaque du personnage
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public PersonnageJouable(Position position, Rectangle rectangle, int attaque){
        super(position, rectangle);
        this.attaque = attaque;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }
    /**
     * Redéfinition du toString
     *
     * @return La phrase que l'on souhaite affiché
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return super.toString() + ", "
                + "attaque = " + attaque + ", ";
    }

    /**
     * Redéfinition du HashCode
     * @return Entier de l'hachage des attributs de PersonnageJouable
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() { return attaque; }

    /**
     * Redéfinition du equals
     * @param obj Objet que l'on veut comparer
     * @return True si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        PersonnageJouable autre = (PersonnageJouable) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param pj PersonnableJouable que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(PersonnageJouable pj) {
        boolean resultat=pj.getAttaque()==attaque;
        return resultat;
    }
}
