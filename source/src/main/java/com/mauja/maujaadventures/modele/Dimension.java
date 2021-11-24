package com.mauja.maujaadventures.modele;

public class Dimension {
    private int largeur;
    private int hauteur;

    /**
     * Constructeur de la classe
     * @param largeur Largeur de l'élément
     * @param hauteur Hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Dimension(int largeur, int hauteur) {
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
    }
    /**
     * Getter de la largeur
     * @return Largeur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getLargeur() { return largeur; }
    /**
     * Setter de la largeur
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    /**
     * Getter de l'hauteur
     * @return L'hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * Setter de l'hauteur
     * @param hauteur Nouvelle hauteur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
}
