package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.action.affiche.Affichable;

import java.util.ArrayList;

public class Carte extends Affichable
{
    private String nom;
    private String id;
    private ArrayList<Calque> listeDeCalques;
    private int largeur;
    private int hauteur;
    /**
     * Constructeur de Carte
     * @param nom Nom de la carte
     * @param id Id de la carte
     * @param largeur Largeur de la carte
     * @param hauteur hauteur de la carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Carte(String nom, String id, int largeur, int hauteur){
        super(""); //A modifier
        this.setNom(nom);
        this.setId(id);
        this.setHauteur(hauteur);
        this.setLargeur(largeur);
        this.listeDeCalques= new ArrayList<Calque>();
    }
    /**
     * Getter de l'hauteur
     * @return L'hauteur de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getHauteur() {
        return hauteur;
    }
    /**
     * Getter de la largeur
     * @return Largeur de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getLargeur() {
        return largeur;
    }
    /**
     * Getter de l'id de la Carte
     * @return L'Id de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getId() {
        return id;
    }
    /**
     * Getter du nom de la Carte
     * @return Renvoie le nom de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getNom() {
        return nom;
    }
    /**
     * Getter de la liste de calques
     * @return La liste des calques
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public ArrayList<Calque> getListeDeCalques() {
        return listeDeCalques;
    }

    /**
     * Ajouter un nouveau calque
     * @param c Calque que l'on veut rajouter
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterCalques(Calque c){
        this.listeDeCalques.add(c);
    }
    /**
     * Setter de l'hauteur
     * @param hauteur Nouvelle hauteur de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }
    /**
     * Setter de l'id de la carte
     * @param id Nouvelle id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setId(String id) {
        this.id = id;
    }
    /**
     * Setter de la largeur de la carte
     * @param largeur Nouvelle largeur que va comporter l'élément
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setLargeur(int largeur) {
        this.largeur = largeur;
    }
    /**
     * Setter du nom de la carte
     * @param nom Nouveau nom de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setNom(String nom) {
        this.nom = nom;
    }
}
