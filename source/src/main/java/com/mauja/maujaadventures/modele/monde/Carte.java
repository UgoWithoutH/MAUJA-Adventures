package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.action.affiche.Affichable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carte extends Affichable {
    public static int nombreIdentifiants = 0;
    private String nom;
    private int id;
    private List<Calque> listeDeCalques;
    private Dimension dimension;

    /**
     * Constructeur de Carte
     *
     * @param nom Nom de la carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Carte(String nom, Dimension dimension, List<Calque> lesCalques) {

        super(""); //A modifier
        this.nom = nom;
        this.id = nombreIdentifiants;
        this.dimension = dimension;
        this.listeDeCalques = new ArrayList<>();
        if (lesCalques != null) {
            listeDeCalques = lesCalques;
        }
        nombreIdentifiants++;
    }

    /**
     * Getter de l'id de la Carte
     *
     * @return L'Id de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public int getId() {
        return id;
    }

    /**
     * Setter de l'id de la carte
     *
     * @param id Nouvelle id de la tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setId(int id) {
        this.id = id;
    }

    /**
     * Getter du nom de la Carte
     *
     * @return Renvoie le nom de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public String getNom() {
        return nom;
    }

    /**
     * Setter du nom de la carte
     *
     * @param nom Nouveau nom de la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la liste de calques
     *
     * @return La liste des calques
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Calque> getListeDeCalques() {
        return listeDeCalques;
    }

    /**
     * Ajouter un nouveau calque
     *
     * @param c Calque que l'on veut rajouter
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterCalques(Calque c) {
        this.listeDeCalques.add(c);
    }

    public Dimension getDimension() {
        return dimension;
    }

    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public int hashCode() {
        return dimension.hashCode() + 31 * nom.hashCode() + id
                + 31 * listeDeCalques.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Carte autre = (Carte) obj;
        return equals(autre);
    }

    public boolean equals(Carte c) {
        boolean resultat = (dimension.equals(c.getDimension()))
                && (Objects.equals(c.getNom(), nom)) && (c.getId() == id)
                && (listeDeCalques.equals(c.getListeDeCalques()));
        return resultat;
    }
}