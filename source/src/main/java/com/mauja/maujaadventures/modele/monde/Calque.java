package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.action.affiche.Affichable;

import java.util.ArrayList;

public class Calque extends Affichable {

    private int hauteur;
    private int largeur;

    private ArrayList<Tuile> listeDeTuiles;
    /**
     * Constructeur de Calque
     * @param largeur Largeur du calque
     * @param hauteur Hauteur du calque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Calque(int largeur, int hauteur) {
        super(""); // A modifier
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.listeDeTuiles = new ArrayList<Tuile>();
    }

    public ArrayList<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
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
    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    /**
     * Getter de la largeur
     * @return Largeur de l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * Setter de la largeur
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    /**
     * Méthode permettant d'ajouter une tuile à la liste
     * @param t Tuile que l'on va rajouter à la liste de tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterTuile(Tuile t){
        this.listeDeTuiles.add(t);
    }

    @Override
    public int hashCode() { return hauteur+largeur; }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Calque autre = (Calque) obj;
        return equals(autre);
    }

    public boolean equals(Calque c) {
        boolean resultat=(c.getHauteur()==hauteur) && (c.getLargeur() == largeur);
        return resultat;
    }
}
