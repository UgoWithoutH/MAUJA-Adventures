package com.mauja.maujaadventures.monde;

import com.mauja.maujaadventures.entites.Entite;
import com.mauja.maujaadventures.interactions.ElementInteractif;
import com.mauja.maujaadventures.logique.Dimension;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Carte {
    public static int nombreIdentifiants = 0;
    private String nom;
    private int id;
    private List<Calque> listeDeCalques;
    private List<ElementInteractif> lesElementsInteractif;
    private Dimension dimension;

    /**
     * Constructeur de la classe Carte
     *
     * @param nom nom Nom de la carte
     * @param dimension Longueur et Hauteur de la carte
     * @param lesCalques Liste des calques appartenant à la Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */


    public Carte(String nom, Dimension dimension, List<Calque> lesCalques, List<ElementInteractif> lesEntites)
            throws IllegalArgumentException {
        if (lesCalques == null) {
            throw new IllegalArgumentException("La carte doit obligatoirement être composée de calques");
        }
        if (lesEntites == null) {
            lesEntites = new ArrayList<>();
        }

        this.nom = nom;
        this.dimension = dimension;
        listeDeCalques = lesCalques;
        this.lesElementsInteractif = lesEntites;
        this.id = nombreIdentifiants;
        nombreIdentifiants++;
    }

    public Carte(String nom, Dimension dimension, List<Calque> lesCalques) throws IllegalArgumentException {
        this(nom, dimension, lesCalques, null);
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
     * @param c Calque que l'on veut rajouter
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterCalques(Calque c) {
        this.listeDeCalques.add(c);
    }

    /**
     * Getter de la dimension
     * @return Les valeurs de la dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Setter de la dimension
     * @param dimension Nouveau valeur de la dimension
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    private void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public List<ElementInteractif> getLesElementsInteractif() {
        return lesElementsInteractif;
    }

    public void ajouterEntite(Entite entite) {
        if (entite != null) {
            lesElementsInteractif.add(entite);
        }
    }

    public void supprimerEntite(Entite entite) {
        lesElementsInteractif.remove(entite);
    }

    /**
     * Redéfinition du Hash Code permet d'avoir une valeurs unique par Carte
     * @return entier de l'Hachage des attributs de Carte
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return dimension.hashCode() + 31 * nom.hashCode() + id
                + 31 * listeDeCalques.hashCode();
    }

    /**
     * Redéfinition du Equals
     * @param obj Objet permettant de faire l'égalité
     * @return Un booléen pour savoir si égalité ou non
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Carte autre = (Carte) obj;
        return equals(autre);
    }

    /**
     * Nouvelle fonction equals
     * @param c Correspond à la carte
     * @return Le résultat de l'égalité
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */

    public boolean equals(Carte c) {
        boolean resultat = (dimension.equals(c.getDimension()))
                && (Objects.equals(c.getNom(), nom)) && (c.getId() == id)
                && (listeDeCalques.equals(c.getListeDeCalques()));
        return resultat;
    }

    /**
     * Redéfinition du ToString
     * @return la chaîne de caractère que l'on veut afficher dans la console
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Carte{" +
                "nom='" + nom + ", id=" + id +
                ", listeDeCalques=" + listeDeCalques.toString() +
                ", dimension=" + dimension.toString() +
                " nombre Identifiant: " + nombreIdentifiants +
                '}';
    }
}