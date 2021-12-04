package com.mauja.maujaadventures.modele;

public class Collision {
    private double largeur;
    private double hauteur;
    private Position position;

    /**
     * Constructeur de collision
     * @param position Position de la collision
     * @param largeur Largeur de la collision
     * @param hauteur Hauteur de la collision
     */
    public Collision(Position position, double largeur, double hauteur){
        this.position = position;
        this.hauteur = hauteur;
        this.largeur = largeur;
    }

    /**
     * Getter de Largeur
     * @return Largeur de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public double getLargeur() { return largeur; }

    /**
     * Getter de Hauteur
     * @return Hauteur de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adriens
     */
    public double getHauteur() { return hauteur; }

    /**
     * Getter de la posion
     * @return Position de la collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Position getPosition() { return position; }

    /**
     * Redéfinition du toString
     * @return chaîne que l'on veut afficher
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Largeur : " + largeur
                + ", Hauteur : " + hauteur
                + ", Position : " + position.toString();
    }

    /**
     * Redéfinition du hashCOde
     * @return Entier de l'hachage des attributs de Collision
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return (int)(hauteur+largeur)+ 31*position.hashCode();
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
        Collision autre = (Collision) obj;
        return equals(autre);
    }

    /**
     * Méthode equals
     * @param c Collision que l'on veut comparer
     * @return true si vrai sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Collision c) {
        boolean resultat= (c.getHauteur() == hauteur) && (c.getLargeur() == largeur ) && ( position.equals(c.getPosition()));
        return resultat ;
    }

}
