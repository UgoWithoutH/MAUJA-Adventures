package com.mauja.maujaadventures.modele.monde;

import com.mauja.maujaadventures.modele.Dimension;
import com.mauja.maujaadventures.modele.action.affiche.Affichable;

import java.util.ArrayList;
import java.util.List;

public class Calque extends Affichable {

    private Dimension dimension;
    private List<Tuile> listeDeTuiles;
    /**
     * Constructeur de Calque
     * @param dimension Dimension du Calque (Hauteur Largeur)
     * @param lesTuiles Liste des tuiles composant le calque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public Calque(Dimension dimension, List<Tuile> lesTuiles) {
        super(""); // A modifier
        this.dimension = dimension;
        this.listeDeTuiles = new ArrayList<Tuile>();
        if (lesTuiles != null) {
            listeDeTuiles = lesTuiles;
        }
    }

    /**
     * Getter de la Liste des Tuiles
     * @return La liste des tuiles du calque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public List<Tuile> getListeDeTuiles() {
        return listeDeTuiles;
    }

    /**
     * Méthode permettant d'ajouter une tuile à la liste
     * @param t Tuile que l'on va rajouter à la liste de tuile
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public void ajouterTuile(Tuile t){
        this.listeDeTuiles.add(t);
    }

    /**
     * Getter de la dimension
     * @return hauteur et largeur du calque
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Redéfinition du HashCode
     * @return Entier de l'hachage des attributs de Calque
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public int hashCode() {
        return dimension.hashCode() + 31 * listeDeTuiles.hashCode();
    }

    /**
     * Redéfinition du equals permet de voir si 2 calques sont égaux
     * @param obj Objet permettant la comparaison
     * @return True si égalité ou false si pas égalité
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(this == obj) return true;
        if (getClass() != obj.getClass()) return false;
        Calque autre = (Calque) obj;
        return equals(autre);
    }

    /**
     * Méthode équals permettant de voir si égalité
     * @param c Correspond au calque que l'on veut vérifier si égalité
     * @return true si égalité sinon false
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    public boolean equals(Calque c) {
        boolean resultat = (dimension.equals(c.getDimension())
                && listeDeTuiles.equals(c.getListeDeTuiles()));
        return resultat;
    }

    /**
     * Redéfinition du toString
     * @return la chaîne de caractère à afficher dans la console
     * @author Tremblay Jeremy, Vignon Ugo, Viton Antoine, Wissocq Maxime, Coudour Adrien
     */
    @Override
    public String toString() {
        return "Calque{" +
                "dimension=" + dimension.toString() +
                ", listeDeTuiles=" + listeDeTuiles.toString() +
                '}';
    }
}
