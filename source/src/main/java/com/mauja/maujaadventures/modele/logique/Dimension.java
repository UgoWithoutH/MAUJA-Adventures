package com.mauja.maujaadventures.modele.logique;

public class Dimension {
    private double largeur;
    private double hauteur;

    /**
     * Constructeur de la classe
     * @param largeur Largeur de l'élément
     * @param hauteur Hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension(double largeur, double hauteur) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }
    /**
     * Getter de la largeur
     * @return Largeur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getLargeur() { return largeur; }
    /**
     * Setter de la largeur
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setLargeur(double largeur) {
        this.largeur = largeur;
    }
    /**
     * Getter de l'hauteur
     * @return L'hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getHauteur() {
        return hauteur;
    }
    /**
     * Setter de l'hauteur
     * @param hauteur Nouvelle hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setHauteur(double hauteur) {
        this.hauteur = hauteur;
    }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return largeur + "x" + hauteur;
    }

    /**
     * Redéfinition du hashCode
     * @return Hachage des attributs de dimension
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return (int) (largeur + hauteur);
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
        Dimension autre = (Dimension) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param m Dimension que l'on veut comparer
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Dimension m) {
        boolean resultat = (m.getHauteur() == hauteur) && (m.getLargeur() == largeur);
        return resultat;
    }
}
